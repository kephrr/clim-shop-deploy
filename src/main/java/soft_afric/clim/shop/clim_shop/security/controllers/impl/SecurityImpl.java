package soft_afric.clim.shop.clim_shop.security.controllers.impl;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import soft_afric.clim.shop.clim_shop.security.controllers.Security;
import soft_afric.clim.shop.clim_shop.security.data.entities.AppUser;
import soft_afric.clim.shop.clim_shop.security.services.SecurityService;
import soft_afric.clim.shop.clim_shop.services.ClientService;

@Controller
@RequiredArgsConstructor
public class SecurityImpl implements Security {
    private final SecurityService securityService;
    @Override
    public String login(UserDetails user) {
        if(user!=null){
            if(user.getAuthorities().stream().anyMatch(c->c.getAuthority().compareTo("Admin")==0)){
                return "redirect:/client/details/1";
            }
            if(user.getAuthorities().stream().anyMatch(c->c.getAuthority().compareTo("Client")==0)){
                AppUser appUser = securityService.getUserByUsername(user.getUsername());
                return "redirect:/client/home";
            }
        }
        return "security/login";
    }
}
