package soft_afric.clim.shop.clim_shop.web.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import soft_afric.clim.shop.clim_shop.web.dto.request.PanierRequestDto;

public interface CommandeController {
    @PostMapping("/commander")
    String Commander(Model model,
                     @ModelAttribute("panier") PanierRequestDto panier);

    @GetMapping("/client/commandes")
    String Commandes(Model model);
}
