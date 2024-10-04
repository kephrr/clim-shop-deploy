package soft_afric.clim.shop.clim_shop.web.controllers.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import soft_afric.clim.shop.clim_shop.data.entities.Client;
import soft_afric.clim.shop.clim_shop.data.entities.Commande;
import soft_afric.clim.shop.clim_shop.data.entities.LigneCommande;
import soft_afric.clim.shop.clim_shop.data.enums.EtatCommande;
import soft_afric.clim.shop.clim_shop.data.enums.ModePaiement;
import soft_afric.clim.shop.clim_shop.services.ClientService;
import soft_afric.clim.shop.clim_shop.services.ClimService;
import soft_afric.clim.shop.clim_shop.services.CommandeService;
import soft_afric.clim.shop.clim_shop.services.LigneCommandeService;
import soft_afric.clim.shop.clim_shop.web.controllers.CommandeController;
import soft_afric.clim.shop.clim_shop.web.dto.request.ClimPanierDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.PanierRequestDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.RechercheDto;
import soft_afric.clim.shop.clim_shop.web.dto.response.CommandeDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"panier"})
public class CommandeControllerImpl implements CommandeController {
    private final CommandeService commandeService;
    private final ClientService clientService;
    private final ClimService climService;
    private final LigneCommandeService ligneCommandeService;
    @Override
    public String Commander(Model model, PanierRequestDto panier) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Client client = clientService.findByUsername(currentUserName);

        Commande commande = new Commande();
        commande.setEtatCommande(EtatCommande.Facturer);
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
        for(ClimPanierDto ligne : panier.getArticles()){
            LigneCommande l = LigneCommande.builder()
                            .clim(climService.show(ligne.getId()).orElseThrow(()->new RuntimeException("Clim"+ligne.getId()+" not found in the service")))
                            .prix(ligne.getPrix())
                            .quantite(ligne.getQuantite())
                            .montant(ligne.getMontant())
                            .build();
            montant += l.getMontant();
            l.setCommande(commande);
            lignes.add(l);
        }
        commande.setMontant(montant);
        commandeService.save(commande);
        for (LigneCommande l : lignes) {
            ligneCommandeService.save(l);
        }

        model.addAttribute("commande", panier.toString());
        return "redirect:client/commandes";
    }

    @Override
    public String Commandes(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        Client client = clientService.findByUsername(currentUserName);
        List<Commande> commandesClient = client.getCommandes();
        List<CommandeDto> commandes = new ArrayList<>(commandesClient.stream().map(CommandeDto::toDto).toList());
        // Collections.reverse(commandes);
        model.addAttribute("commandes", commandes);
        model.addAttribute("msg", "Voici vos commandes !!");
        setSearchBarDto(model);
        return "client/commandes";
    }

    public void setSearchBarDto(Model model){
        model.addAttribute("search", new RechercheDto());
    }
}
