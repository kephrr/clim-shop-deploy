package soft_afric.clim.shop.clim_shop.web.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import soft_afric.clim.shop.clim_shop.security.data.entities.AppUser;
import soft_afric.clim.shop.clim_shop.web.dto.request.FilterDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.PanierRequestDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.RechercheDto;

public interface ClimController {

    @GetMapping("/home")
    String homePage(Model model);

    @GetMapping("/details/{id}")
    String detailsPage(Model model,
                    @PathVariable Long id);

    @PostMapping("/home/filter")
    String homeFilterClims(Model model,
                           @ModelAttribute FilterDto filterDto);

    @PostMapping("/home/search")
    String homeFilterClims(Model model,
                           @ModelAttribute RechercheDto rechercheDto);
}
