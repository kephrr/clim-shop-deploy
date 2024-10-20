package soft_afric.clim.shop.clim_shop.web.controllers.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import soft_afric.clim.shop.clim_shop.data.entities.Adresse;
import soft_afric.clim.shop.clim_shop.data.entities.Client;
import soft_afric.clim.shop.clim_shop.data.entities.Commande;
import soft_afric.clim.shop.clim_shop.data.entities.LigneCommande;
import soft_afric.clim.shop.clim_shop.data.enums.EtatCommande;
import soft_afric.clim.shop.clim_shop.data.enums.ModePaiement;
import soft_afric.clim.shop.clim_shop.security.services.SecurityService;
import soft_afric.clim.shop.clim_shop.services.ClientService;
import soft_afric.clim.shop.clim_shop.services.ClimService;
import soft_afric.clim.shop.clim_shop.services.CommandeService;
import soft_afric.clim.shop.clim_shop.services.LigneCommandeService;
import soft_afric.clim.shop.clim_shop.web.controllers.CommandeController;
import soft_afric.clim.shop.clim_shop.web.dto.request.ClientRequestDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.ClimPanierDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.PanierRequestDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.RechercheDto;
import soft_afric.clim.shop.clim_shop.web.dto.response.CommandeDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"panier"})
public class CommandeControllerImpl implements CommandeController {
    private final CommandeService commandeService;
    private final ClientService clientService;
    private final SecurityService securityService;
    private final ClimService climService;
    private final LigneCommandeService ligneCommandeService;
    private  final PasswordEncoder passwordEncoder;
    @Override
    public String Commander(Model model, PanierRequestDto panier) {
        Client client = clientService.findByNumTel(panier.getClient().getTel())
                .orElse(clientService.findByUsername(panier.getClient().getNomComplet()));
        if(client==null) {
                String[] adresses = panier.getClient().getAdresse().split(" ");
                client = Client.builder()
                        .nomComplet(panier.getClient().getNomComplet())
                        .tel(panier.getClient().getTel())
                        .adresse(new Adresse(adresses[0], adresses[1], adresses[2]))
                        .build();
                client.setLogin(client.getNomComplet());
                client.setPassword(passwordEncoder.encode(client.getTel()));
                // securityService.addUser(client.getLogin(), client.getPassword());
                clientService.save(client);
                securityService.addRoleToUser(client.getLogin(), "Client");
        }
        Commande commande = new Commande();
        commande.setEtatCommande(EtatCommande.Encour);
        commande.setClient(client);
        commande.setModePaiement(ModePaiement.values()[panier.getModePaiement()]);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateString = formatter.format(date);
        Date parsedDate = null;
        try {
            parsedDate = formatter.parse(dateString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        commande.setDateCommmande(parsedDate);

        List<LigneCommande> lignes = new ArrayList<>();
        int montant = 0;
        if(panier.getFrais()){
            montant += 35000;
        }
        for(ClimPanierDto ligne : panier.getArticles()){
            LigneCommande l = LigneCommande.builder()
                            .clim(climService.show(ligne.getId()).orElseThrow(()->new RuntimeException("Clim"+ligne.getId()+" not found in the service")))
                            .prix(ligne.getPrix())
                            .quantite(ligne.getQuantite())
                            .montant(ligne.getMontant())
                            .build();
            l.setCommande(commande);
            montant += l.getMontant();
            lignes.add(l);
        }
        commande.setMontant(montant);
        commandeService.save(commande);
        commande.setLigneCommandes(lignes);
        for (LigneCommande l : lignes) {
            ligneCommandeService.save(l);
        }
        List<Commande> commandesClient = commandeService.findAll(client);
        List<CommandeDto> commandes = new ArrayList<>(commandesClient.stream().map(CommandeDto::toDto).toList());

        model.addAttribute("commandes", commandes);
        model.addAttribute("commande", panier.toString());
        model.addAttribute("msg", "Voici votre commande!!");
        setSearchBarDto(model);
        model.addAttribute("user",getCurrentUsername());
        return "public/commandes";
    }

    public void setSearchBarDto(Model model){
        model.addAttribute("search", new RechercheDto());
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }
}
