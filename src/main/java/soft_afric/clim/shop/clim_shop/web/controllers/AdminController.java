package soft_afric.clim.shop.clim_shop.web.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import soft_afric.clim.shop.clim_shop.web.dto.request.ClientRequestDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.ClimCreateDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.FilterDto;

public interface AdminController {
    // Voir toutes les clims
    @GetMapping("/")
    String index();
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
    String allClients(Model model);

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

    // Voir toutes les dommandes
    @GetMapping("/commandes/{client}")
    String allCommandes(Model model, @PathVariable Long client);

    @PostMapping("/commandes/filter")
    String CommandesFilterState(Model model,
                           @ModelAttribute FilterDto filterDto);

    // marquer une commande comme livree
    @GetMapping("/commandes/validate/{id}")
    String validateCommande(Model model, @PathVariable Long id);

    // archiver une commande
    @GetMapping("/commandes/delete/{id}")
    String deleteCommande(Model model, @PathVariable Long id);

}
