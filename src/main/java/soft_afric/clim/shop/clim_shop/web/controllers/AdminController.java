package soft_afric.clim.shop.clim_shop.web.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import soft_afric.clim.shop.clim_shop.web.dto.request.ClientRequestDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.ClimCreateDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.FilterDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.FournisseurCreateDto;

public interface AdminController {
    // Voir toutes les clims
    @GetMapping("/")
    String index(Model model);
    // Voir toutes les clims
    @GetMapping("/clims")
    String allClims(Model model);

    // Enregistrer une clim
    @PostMapping("/clims")
    String saveClim(Model model,
                    @ModelAttribute ClimCreateDto climCreateDto);
    // Archiver une clim
    @GetMapping("/clims/delete/{id}")
    String deleteClim(Model model, @PathVariable Long id);

    // Modifier une clim form
    @GetMapping("/clims/edit/{id}")
    String editClim(Model model, @PathVariable Long id);

    // En registrer la modification
    @PostMapping("/clims/edit")
    String updateClim(Model model,
                      @ModelAttribute ClimCreateDto climCreateDto);

    // GESTION DES CLIENTS
    // Voir tous les clients
    @GetMapping("/clients")
    String allClients(Model model,
                      @RequestParam(defaultValue = "") String tel);

    // Modifier un client form
    @GetMapping("/clients/edit/{id}")
    String editClient(Model model, @PathVariable Long id);

    // Modifier un client form
    @PostMapping("/clients/edit")
    String updateClient(Model model,
                        @ModelAttribute ClientRequestDto clientRequestDto);

    // Modifier un client
    @PostMapping("/clients")
    String saveClient(Model model);

    // GESTION DES COMMANDES
    // Voir toutes les dommandes
    @GetMapping("/commandes")
    String allCommandes(Model model);

    // Voir les details d'une commande
    @GetMapping("/commandes/details/{id}")
    String detailsCommande(Model model, @PathVariable Long id);

    // Voir toutes les dommandes d'un client
    @GetMapping("/commandes/{client}")
    String allCommandesByClient(Model model, @PathVariable Long client);

    @PostMapping("/commandes/filter")
    String CommandesFilterState(Model model,
                           @ModelAttribute FilterDto filterDto);

    // marquer une commande comme livree
    @GetMapping("/commandes/validate/{id}")
    String validateCommande(Model model, @PathVariable Long id);

    // archiver une commande
    @GetMapping("/commandes/delete/{id}")
    String deleteCommande(Model model, @PathVariable Long id);

    // GESTION DES FOURNISSEURS
    // Voir tous les fournisseurs
    @GetMapping("/fournisseurs")
    String allFournisseurs(Model model);

    // Voir les details d'un fournisseur
    @GetMapping("/fournisseurs/details/{id}")
    String detailsFournisseurs(Model model, @PathVariable Long id);

    // Editer un fournisseur
    @GetMapping("/fournisseurs/edit/{id}")
    String editFournisseurs(Model model, @PathVariable Long id);

    @PostMapping("/fournisseurs/edit")
    String editFournisseurs(Model model, @ModelAttribute FournisseurCreateDto fournisseurCreateDto);

    @GetMapping("/fournisseurs/add")
    String formFournisseurs(Model model);

    @PostMapping("/fournisseurs/add")
    String saveFournisseurs(Model model,  @ModelAttribute FournisseurCreateDto fournisseurCreateDto);

}
