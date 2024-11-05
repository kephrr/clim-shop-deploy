package soft_afric.clim.shop.clim_shop.web.controllers.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import soft_afric.clim.shop.clim_shop.data.entities.*;
import soft_afric.clim.shop.clim_shop.security.services.SecurityService;
import soft_afric.clim.shop.clim_shop.services.*;
import soft_afric.clim.shop.clim_shop.web.controllers.ClimController;
import soft_afric.clim.shop.clim_shop.web.dto.request.ClimPanierDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.FilterDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.PanierRequestDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.RechercheDto;
import soft_afric.clim.shop.clim_shop.web.dto.response.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequiredArgsConstructor
@SessionAttributes({"panier"})
public class ClimControllerImpl implements ClimController {
    private final CommentaireService commentaireService;
    private final ClimService climService;
    private final CategorieService categorieService;
    private final MarqueService marqueService;

    @Override
    public String homePage(Model model) {
        List<ClimDto> allClims = climService.findAll()
                .stream().
                map(ClimDto::toCardDto).toList();
        firstPageData(model, allClims, "Nos produits populaires");
        model.addAttribute("filter", new FilterDto());
        setSearchBarDto(model);
        return "public/home";
    }

    @Override
    public String detailsPage(Model model, Long id) {
        Clim clim = climService.show(id).orElse(null);
        if(clim==null){
            return "redirect:/home";
        }
        ClimDto climDetails = ClimDto.toDetailsDto(clim);
        ClimPanierDto climPanierDto = ClimPanierDto.toDto(climDetails);
        model.addAttribute("climPanierDto", climPanierDto);
        model.addAttribute("climDetails", climDetails);
        setSearchBarDto(model);
        model.addAttribute("user",getCurrentUsername());
        return "public/clim-details";
    }

    @Override
    public String homeFilterClims(Model model, FilterDto filterDto) {
        Categorie categorie = categorieService.show(filterDto.getCategorieID()).orElse(null);
        Marque marque = marqueService.show(filterDto.getMarqueID()).orElse(null);
        List<ClimDto> allClims = climService
                .findAllByCategorieAndMarqueAndBudget(categorie, marque, filterDto.getBudget())
                .stream().map(ClimDto::toCardDto).toList();
        model.addAttribute("filter", filterDto);
        firstPageData(model, allClims, "Vos recherches");
        model.addAttribute("user",getCurrentUsername());
        setSearchBarDto(model);
        return "public/home";
    }

    @Override
    public String homeFilterClims(Model model, RechercheDto rechercheDto) {
        List<ClimDto> allClims = climService.findAllBySearchedKeyword(rechercheDto.getKeyword()).stream().map(ClimDto::toCardDto).toList();

        model.addAttribute("user",getCurrentUsername());
        model.addAttribute("filter", new FilterDto());
        firstPageData(model, allClims, "Vos recherches");
        model.addAttribute("search", rechercheDto);
        return "public/home";
    }

    public void firstPageData(Model model, List<ClimDto> allClims, String search_title) {
        List<ClimDto> promotedClims = climService.findAllPromotedClims().stream().map(ClimDto::toCardDto).toList();
        List<CategorieDto> categories = categorieService.findAll().stream().map(CategorieDto::toDto).toList();
        List<MarqueDto> marques = marqueService.findAll().stream().map(MarqueDto::toDto).toList();
        List<CommentaireDto> commentaires = commentaireService.findFourthBetterComments()
                .stream()
                .map(CommentaireDto::toDto).toList();

        model.addAttribute("comments", commentaires);
        model.addAttribute("user",getCurrentUsername());
        model.addAttribute("marques", marques);
        model.addAttribute("categories", categories);
        model.addAttribute("climsPromoted", promotedClims);
        model.addAttribute("clims", allClims);
        model.addAttribute("search_title", search_title);
    }

    public void setSearchBarDto(Model model){
        model.addAttribute("search", new RechercheDto());
    }

    @ModelAttribute("panier")
    public PanierRequestDto panier(){
        return new PanierRequestDto(
                    new ArrayList<>(),
                    35000,
                    ClientDto.toDto(Client.builder()
                            .nomComplet("")
                            .tel("")
                            .adresse(new Adresse("Dakar","quartier","000"))
                            .build()),
                false,
                    new Date(),
                    0, 0);
    }

    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }
}
