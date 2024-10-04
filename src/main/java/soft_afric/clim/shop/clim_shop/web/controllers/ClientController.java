package soft_afric.clim.shop.clim_shop.web.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import soft_afric.clim.shop.clim_shop.web.dto.request.ClientRequestDto;

public interface ClientController {
    @GetMapping("/account")
    String account(Model model);

    @PostMapping("/account")
    String modifyAccount(
            Model model,
            @ModelAttribute ClientRequestDto clientEditDto
            );

    @GetMapping("/signup/form")
    String signupForm(Model model);
}
