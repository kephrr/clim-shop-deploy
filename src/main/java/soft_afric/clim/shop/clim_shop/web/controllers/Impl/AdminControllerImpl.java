package soft_afric.clim.shop.clim_shop.web.controllers.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import soft_afric.clim.shop.clim_shop.data.entities.*;
import soft_afric.clim.shop.clim_shop.data.enums.EtatClim;
import soft_afric.clim.shop.clim_shop.data.enums.EtatCommande;
import soft_afric.clim.shop.clim_shop.services.*;
import soft_afric.clim.shop.clim_shop.web.controllers.AdminController;
import soft_afric.clim.shop.clim_shop.web.dto.request.ClientRequestDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.ClimCreateDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.FilterDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.FournisseurCreateDto;
import soft_afric.clim.shop.clim_shop.web.dto.response.*;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminControllerImpl implements AdminController {
    private final ClimService climService;
    private final MarqueService marqueService;
    private final CategorieService categorieService;
    private final CommandeService commandeService;
    private final ClientService clientService;
    private final FournisseurService fournisseurService;

    @Override
    public String index(Model model) {
        List<Clim> clims = climService.findAll();
        List<Client> clients = clientService.findAll();
        List<Commande> commandes = commandeService.findAll();
        DashBoardDto dashBoard = DashBoardDto.toDto(commandes, clients, clims);
        model.addAttribute("dashboard", dashBoard);
        return "admin/dashboard";
    }

    // GESTION DES CLIMATISEURS

    @Override
    public String allClims(Model model) {
        List<ClimDto> clims = climService.findAll().stream().map(ClimDto::toDetailsDto).toList();
        setSelectForClim(model);
        model.addAttribute("climCreate", new ClimCreateDto());
        model.addAttribute("clims", clims);
        return "admin/clim";
    }

    @Override
    public String saveClim(Model model, @ModelAttribute ClimCreateDto climCreateDto) {
        Clim clim = ClimCreateDto.ToEntity(climCreateDto);
        Marque m = marqueService.show(climCreateDto.getMarque()).orElse(null);
        clim.setMarque(m);
        Categorie c = categorieService.show(climCreateDto.getCategorie()).orElse(null);
        clim.setCategorie(c);
        climService.save(clim);
        return "redirect:/admin/clims";
    }

    @Override
    public String deleteClim(Model model, Long id) {
        Clim clim = climService.show(id).orElse(null);
        assert clim != null;
        clim.setIsActived(false);
        climService.save(clim);
        return "redirect:/admin/clims";
    }

    @Override
    public String editClim(Model model, Long id) {
        Optional<Clim> climOptional = climService.show(id);
        if(climOptional.isEmpty()){
            return "redirect:/admin/clims";
        }else{
            ClimCreateDto clim = ClimCreateDto.toDto(climOptional.get());
            model.addAttribute("climEdit", clim);
            model.addAttribute("climDetails", ClimDto.toDetailsDto(climOptional.get()));
            setSelectForClim(model);
            return "admin/clim-edit";
        }
    }

    @Override
    public String updateClim(Model model, @ModelAttribute ClimCreateDto climCreateDto) {
        Clim clim = climService.show(climCreateDto.getId()).orElse(null);
        // recup la clim et modifier
        clim.setLibelle(climCreateDto.getLibelle());
        clim.setImage(climCreateDto.getImage());
        clim.setCategorie(categorieService.show(climCreateDto.getCategorie()).orElse(null));

        climService.save(clim);
        return "";
    }

    // GESTION DES CLIENTS

    @Override
    public String allClients(Model model) {
        List<ClientDto> clients = clientService.findAll().stream().map(ClientDto::toDto).toList();
        model.addAttribute("clients", clients);
        return "admin/client";
    }

    @Override
    public String editClient(Model model, Long id) {
        Client client = clientService.show(id).orElse(null);
        assert client != null;
        ClientRequestDto clientEdit = ClientRequestDto.toDto(client);
        model.addAttribute("clientEdit", clientEdit);
        return "admin/client-edit";
    }

    @Override
    public String updateClient(Model model, @ModelAttribute ClientRequestDto clientRequestDto) {
        Client client = clientService.findByNumero(clientRequestDto.getNumero()).orElse(null);
        assert client != null;
        client.setNomComplet(clientRequestDto.getNomComplet());
        client.setTel(clientRequestDto.getTel());
        client.setAdresse(new Adresse(
                clientRequestDto.getVille(),
                clientRequestDto.getQuartier(),
                clientRequestDto.getVilla()
        ));
        client.setNumero(clientRequestDto.getNumero());
        client.setCommentaire(clientRequestDto.getComment());
        clientService.save(client);
        model.addAttribute("clientEdit", clientRequestDto);
        return "admin/client-edit";
    }

    @Override
    public String saveClient(Model model) {
        return "";
    }

    // GESTION DES COMMANDES

    @Override
    public String allCommandes(Model model) {
        List<CommandeDto> commandes = commandeService.findAll().stream().map(CommandeDto::toAdminDto).toList();
        model.addAttribute("commandes", reverseList(commandes));
        model.addAttribute("title", "Toutes les commandes");
        model.addAttribute("filter", new FilterDto());
        return "admin/commande";
    }

    @Override
    public String detailsCommande(Model model, Long id) {
        Commande cmd = commandeService.show(id).orElseThrow(()-> new RuntimeException("Commande "+id+" introuvable"));
        cmd.setEtatCommande(EtatCommande.Livree);
        CommandeDto commandeDto = CommandeDto.toAdminDetailsDto(cmd);
        model.addAttribute("c", commandeDto);
        return "admin/commande-details";
    }

    @Override
    public String allCommandesByClient(Model model, Long client) {
        Client cl = clientService.show(client).orElseThrow(()-> new RuntimeException("Client "+client+" introuvable"));
        List<CommandeDto> commandes = commandeService.findAll(cl).stream().map(CommandeDto::toAdminDto).toList();
        model.addAttribute("commandes", reverseList(commandes));
        model.addAttribute("title", "Commandes de "+cl.getNomComplet());
        model.addAttribute("filter", new FilterDto());
        return "admin/commande";
    }

    @Override
    public String CommandesFilterState(Model model, FilterDto filterDto) {
        Long index = filterDto.getId();
        if(index!=-1){
            EtatCommande etat = EtatCommande.values()[filterDto.getId().intValue()];
            List<CommandeDto> commandes = commandeService.findAll(etat).stream().map(CommandeDto::toAdminDto).toList();
            model.addAttribute("commandes", reverseList(commandes));
            model.addAttribute("filter", new FilterDto());
            return "admin/commande";
        }
        return "redirect:/admin/commandes";
    }

    @Override
    public String validateCommande(Model model, Long id) {
        Commande cmd = commandeService.show(id).orElseThrow(()-> new RuntimeException("Commande "+id+" introuvable"));
        cmd.setEtatCommande(EtatCommande.Livree);
        commandeService.save(cmd);
        return "redirect:/admin/commandes";
    }

    @Override
    public String deleteCommande(Model model, Long id) {
        Commande cmd = commandeService.show(id).orElseThrow(()-> new RuntimeException("Commande "+id+" introuvable"));
        cmd.setIsActived(false);
        commandeService.save(cmd);
        return "redirect:/admin/commandes";
    }

    // GESTION DES FOURNISSEURS
    @Override
    public String allFournisseurs(Model model) {
        List<Fournisseur> data = fournisseurService.findAll();
        List<FournisseurDto> fournisseurs = data.stream().map(FournisseurDto::toListDto).toList();
        model.addAttribute("fournisseurs", fournisseurs);
        return "admin/fournisseurs";
    }

    @Override
    public String detailsFournisseurs(Model model, Long id) {
        Fournisseur data = fournisseurService.show(id).orElseThrow(()-> new RuntimeException("Fournisseur "+id+" introuvable"));
        FournisseurDto fournisseur = FournisseurDto.toDetailsDto(data);
        model.addAttribute("fournisseur", fournisseur);
        return "admin/fournisseurs-details";
    }

    @Override
    public String formFournisseurs(Model model) {
        return "admin/fournisseurs-form";
    }

    @Override
    public String saveFournisseurs(Model model,  FournisseurCreateDto fournisseurCreateDto) {
        return "admin/fournisseurs";
    }

    // UTILS

    public void setSelectForClim(Model model) {
        List<MarqueDto> marques = marqueService.findAll().stream().map(MarqueDto::toDto).toList();
        List<CategorieDto> categories = categorieService.findAll().stream().map(CategorieDto::toDto).toList();
        List<EtatClimDto> etats = Arrays.stream(EtatClim.values()).map(EtatClimDto::toDto).toList();

        model.addAttribute("marques", marques);
        model.addAttribute("categories", categories);
        model.addAttribute("etats", etats);
    }


    public List<CommandeDto> reverseList(List<CommandeDto> list) {
        return list.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        lst -> {
                            Collections.reverse(lst);
                            return lst;
                        }
                ));
    }
}