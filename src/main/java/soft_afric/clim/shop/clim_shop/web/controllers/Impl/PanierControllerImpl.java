package soft_afric.clim.shop.clim_shop.web.controllers.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import soft_afric.clim.shop.clim_shop.data.entities.Clim;
import soft_afric.clim.shop.clim_shop.services.ClientService;
import soft_afric.clim.shop.clim_shop.services.ClimService;
import soft_afric.clim.shop.clim_shop.web.controllers.PanierController;
import soft_afric.clim.shop.clim_shop.web.dto.request.ClimPanierDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.PanierRequestDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.RechercheDto;
import soft_afric.clim.shop.clim_shop.web.dto.response.ClientDto;
import soft_afric.clim.shop.clim_shop.web.dto.response.ClimDto;

import java.util.ArrayList;


@Controller
@RequiredArgsConstructor
@RequestMapping("/client/panier")
@SessionAttributes({"panier"})
public class PanierControllerImpl implements PanierController {
    private final ClimService climService;
    private final ClientService clientService;
    @Override
    public String addClimToPanier(Model model, ClimPanierDto article, PanierRequestDto panier) {
        Clim product = climService.show(article.getId()).orElseThrow(
                ()->new RuntimeException("Clim is"+article.getId().toString()+" not found")
        );
        if(product != null){
            if(article.getQuantite() <= product.getQteStock()){
                // Ajouter l'article au panier
                article.setImage(product.getImage());
                article.setLibelle(product.getLibelle());
                article.setPrix(article.getPrix()==0? product.getPrix():article.getPrix());
                panier.addClimToPanier(article);
                panier.setNbre(panier.getArticles().size());
            }else{
                return "redirect:details/"+article.getId();
            }
        }
        return panier(model, panier);
    }

    @Override
    public String createClimAndAddPanier(Model model, Long id, PanierRequestDto panier) {
        Clim clim = climService.show(id).orElseThrow(
                ()->new RuntimeException("Clim is"+id+" not found")
        );
        if(clim != null){
            ClimPanierDto article = ClimPanierDto.toDto(ClimDto.toDetailsDto(clim));
            // Ajouter l'article au panier
            article.setQuantite(1);
            panier.addClimToPanier(article);
        }else{
            return "redirect:details/"+id;
        }
        return panier(model, panier);
    }

    @Override
    public String removeClimFromPanier(Model model, Long id, PanierRequestDto panier) {
        panier.removeClimFromPanier(id);
        return panier(model, panier);
    }

    @Override
    public String panier(Model model,
                         @ModelAttribute("panier") PanierRequestDto panier) {
        model.addAttribute("panier", panier);
        model.addAttribute("lignes", panier.getArticles());
        model.addAttribute("client", panier.getClient());
        setSearchBarDto(model);
        return "client/shopping-cart";
    }

    public void setSearchBarDto(Model model){
        model.addAttribute("search", new RechercheDto());
    }
    @ModelAttribute("panier")
    public PanierRequestDto panier(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        return  new PanierRequestDto(
                new ArrayList<>(),
                0.0,
                ClientDto.toDto(clientService.findByUsername(currentUserName)),
                0,
                0
        );
    }
}
