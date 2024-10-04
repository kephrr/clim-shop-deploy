package soft_afric.clim.shop.clim_shop.web.controllers.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import soft_afric.clim.shop.clim_shop.data.entities.*;
import soft_afric.clim.shop.clim_shop.data.enums.EtatClim;
import soft_afric.clim.shop.clim_shop.services.CategorieService;
import soft_afric.clim.shop.clim_shop.services.ClimService;
import soft_afric.clim.shop.clim_shop.services.MarqueService;
import soft_afric.clim.shop.clim_shop.web.controllers.AdminController;
import soft_afric.clim.shop.clim_shop.web.dto.request.ClientRequestDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.ClimCreateDto;
import soft_afric.clim.shop.clim_shop.web.dto.response.CategorieDto;
import soft_afric.clim.shop.clim_shop.web.dto.response.ClimDto;
import soft_afric.clim.shop.clim_shop.web.dto.response.EtatClimDto;
import soft_afric.clim.shop.clim_shop.web.dto.response.MarqueDto;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminControllerImpl implements AdminController {
    private final ClimService climService;
    private final MarqueService marqueService;
    private final CategorieService categorieService;
    @Override
    public String allClims(Model model) {
        List<ClimDto> clims = climService.findAll().stream().map(ClimDto::toDetailsDto).toList();
        setSelectForClim(model);
        model.addAttribute("climCreate", new ClimCreateDto());
        model.addAttribute("clims", clims);
        return "admin/clim";
    }

    @Override
    public String saveClim(Model model, @ModelAttribute ClimCreateDto climCreateDto) {
        Clim clim = ClimCreateDto.ToEntity(climCreateDto);
        Marque m = marqueService.show(climCreateDto.getMarque()).orElse(null);
        clim.setMarque(m);
        Categorie c = categorieService.show(climCreateDto.getCategorie()).orElse(null);
        clim.setCategorie(c);
        climService.save(clim);
        return "redirect:/admin/clims";
    }

    @Override
    public String deleteClim(Model model, Long id) {
        Clim clim = climService.show(id).orElse(null);
        assert clim != null;
        clim.setIsActived(false);
        climService.save(clim);
        return "redirect:/admin/clims";
    }

    @Override
    public String editClim(Model model, Long id) {
        Optional<Clim> climOptional = climService.show(id);
        if(climOptional.isEmpty()){
            return "redirect:/admin/clims";
        }else{
            ClimCreateDto clim = ClimCreateDto.toDto(climOptional.get());
            model.addAttribute("climEdit", clim);
            model.addAttribute("climDetails", ClimDto.toDetailsDto(climOptional.get()));
            setSelectForClim(model);
            return "admin/clim-edit";
        }
    }

    @Override
    public String updateClim(Model model, @ModelAttribute ClimCreateDto climCreateDto) {
        Clim clim = climService.show(climCreateDto.getId()).orElse(null);
        // recup la clim et modifier
        clim.setLibelle(climCreateDto.getLibelle());
        clim.setImage(climCreateDto.getImage());
        clim.setCategorie(categorieService.show(climCreateDto.getCategorie()).orElse(null));

        climService.save(clim);
        return "";
    }

    @Override
    public String allClients(Model model) {
        return "";
    }

    @Override
    public String saveClient(Model model) {
        return "";
    }

    @Override
    public String allCommandes(Model model) {
        return "";
    }

    @Override
    public String saveCommande(Model model) {
        return "";
    }

    public void setSelectForClim(Model model) {
        List<MarqueDto> marques = marqueService.findAll().stream().map(MarqueDto::toDto).toList();
        List<CategorieDto> categories = categorieService.findAll().stream().map(CategorieDto::toDto).toList();
        List<EtatClimDto> etats = Arrays.stream(EtatClim.values()).map(EtatClimDto::toDto).toList();

        model.addAttribute("marques", marques);
        model.addAttribute("categories", categories);
        model.addAttribute("etats", etats);
    }
}
