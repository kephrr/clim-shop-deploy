package soft_afric.clim.shop.clim_shop.web.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import soft_afric.clim.shop.clim_shop.web.dto.request.ClientRequestDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.ClimCreateDto;

public interface AdminController {
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
    @GetMapping("/clims/edit")
    String updateClim(Model model,
                      @ModelAttribute ClimCreateDto climCreateDto);

    // Voir tous les clients
    @GetMapping("/clients")
    String allClients(Model model);

    // Modifier un client
    @PostMapping("/clients")
    String saveClient(Model model);


    // Voir tous les clients
    @GetMapping("/commandes")
    String allCommandes(Model model);

    // Modifier un client
    @PostMapping("/commandes")
    String saveCommande(Model model);
}
