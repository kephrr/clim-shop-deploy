package soft_afric.clim.shop.clim_shop.web.controllers.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import soft_afric.clim.shop.clim_shop.data.entities.Categorie;
import soft_afric.clim.shop.clim_shop.data.entities.Clim;
import soft_afric.clim.shop.clim_shop.data.entities.Marque;
import soft_afric.clim.shop.clim_shop.services.CategorieService;
import soft_afric.clim.shop.clim_shop.services.ClientService;
import soft_afric.clim.shop.clim_shop.services.ClimService;
import soft_afric.clim.shop.clim_shop.services.MarqueService;
import soft_afric.clim.shop.clim_shop.web.controllers.ClimController;
import soft_afric.clim.shop.clim_shop.web.dto.request.ClimPanierDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.FilterDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.PanierRequestDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.RechercheDto;
import soft_afric.clim.shop.clim_shop.web.dto.response.CategorieDto;
import soft_afric.clim.shop.clim_shop.web.dto.response.ClientDto;
import soft_afric.clim.shop.clim_shop.web.dto.response.ClimDto;
import soft_afric.clim.shop.clim_shop.web.dto.response.MarqueDto;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
@SessionAttributes({"panier"})
public class ClimControllerImpl implements ClimController {
    private final ClimService climService;
    private final CategorieService categorieService;
    private final MarqueService marqueService;
    private final ClientService clientService;


    @Override
    public String homePage(Model model) {
        List<ClimDto> allClims = climService.findAll().stream().map(ClimDto::toCardDto).toList();
        firstPageData(model, allClims);
        model.addAttribute("filter", new FilterDto());
        setSearchBarDto(model);
        return "client/home";
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
        return "client/clim-details";
    }

    @Override
    public String homeFilterClims(Model model, FilterDto filterDto) {
        Categorie categorie = categorieService.show(filterDto.getCategorieID()).orElse(null);
        Marque marque = marqueService.show(filterDto.getMarqueID()).orElse(null);
        List<ClimDto> allClims = climService
                .findAllByCategorieAndMarqueAndBudget(categorie, marque, filterDto.getBudget())
                .stream().map(ClimDto::toCardDto).toList();
        model.addAttribute("filter", filterDto);
        firstPageData(model, allClims);
        setSearchBarDto(model);
        return "client/home";
    }

    @Override
    public String homeFilterClims(Model model, RechercheDto rechercheDto) {
        List<ClimDto> allClims = climService.findAllBySearchedKEyword(rechercheDto.getKeyword()).stream().map(ClimDto::toCardDto).toList();

        model.addAttribute("filter", new FilterDto());
        firstPageData(model, allClims);
        model.addAttribute("search", rechercheDto);
        return "client/home";
    }

    public void firstPageData(Model model, List<ClimDto> allClims) {
        List<ClimDto> promotedClims = climService.findAllPromotedClims().stream().map(ClimDto::toCardDto).toList();
        List<CategorieDto> categories = categorieService.findAll().stream().map(CategorieDto::toDto).toList();
        List<MarqueDto> marques = marqueService.findAll().stream().map(MarqueDto::toDto).toList();

        model.addAttribute("marques", marques);
        model.addAttribute("categories", categories);
        model.addAttribute("climsPromoted", promotedClims);
        model.addAttribute("clims", allClims);
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
