package soft_afric.clim.shop.clim_shop.web.controllers;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import soft_afric.clim.shop.clim_shop.web.dto.request.ClimPanierDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.PanierRequestDto;

public interface PanierController {
    @PostMapping("/add")
    String addClimToPanier(Model model,
                            @Valid ClimPanierDto article,
                            @ModelAttribute("panier") PanierRequestDto panier);

    @GetMapping("/add/{id}")
    String createClimAndAddPanier(Model model,
                           @PathVariable Long id,
                           @ModelAttribute("panier") PanierRequestDto panier);

    @GetMapping("/remove/{id}")
    String removeClimFromPanier(Model model,
                                  @PathVariable Long id,
                                  @ModelAttribute("panier") PanierRequestDto panier);

    @GetMapping("")
    String panier(Model model,
                  @ModelAttribute("panier") PanierRequestDto panier
                  );
}
