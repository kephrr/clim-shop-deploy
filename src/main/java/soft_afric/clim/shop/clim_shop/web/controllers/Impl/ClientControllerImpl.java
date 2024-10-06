package soft_afric.clim.shop.clim_shop.web.controllers.Impl;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import soft_afric.clim.shop.clim_shop.data.entities.Adresse;
import soft_afric.clim.shop.clim_shop.data.entities.Client;
import soft_afric.clim.shop.clim_shop.data.entities.Commande;
import soft_afric.clim.shop.clim_shop.services.ClientService;
import soft_afric.clim.shop.clim_shop.web.controllers.ClientController;
import soft_afric.clim.shop.clim_shop.web.dto.request.ClientRequestDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.RechercheDto;
import soft_afric.clim.shop.clim_shop.web.dto.response.ClientDto;
import soft_afric.clim.shop.clim_shop.web.dto.response.CommandeDto;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientControllerImpl implements ClientController {
    private final ClientService clientService;
    @Override
    public String account(Model model) {
        ClientRequestDto client = ClientRequestDto.toDto(getConnectedUser());
        setSearchBarDto(model);
        model.addAttribute("user", client);
        return "client/account";
    }

    @Override
    public String modifyAccount(Model model, @ModelAttribute ClientRequestDto clientEditDto) {
        Client client = getConnectedUser();
        client.setAdresse(new Adresse(clientEditDto.getQuartier(), clientEditDto.getVille(), clientEditDto.getVilla()));
        client.setNomComplet(clientEditDto.getNomComplet());
        client.setTel(clientEditDto.getTel());

        clientService.save(client);
        setSearchBarDto(model);
        model.addAttribute("user", ClientRequestDto.toDto(client));
        return "client/account";
    }

    @Override
    public String signupForm(Model model) {
        return "security/register";
    }

    @Override
    public String Commandes(Model model) {
        Client client = getConnectedUser();
        List<Commande> commandesClient = client.getCommandes();
        List<CommandeDto> commandes = new ArrayList<>(commandesClient.stream().map(CommandeDto::toDto).toList());
        // Collections.reverse(commandes);
        model.addAttribute("commandes", commandes);
        model.addAttribute("msg", "Voici vos commandes !!");
        setSearchBarDto(model);
        return "public/commandes";
    }

    public void setSearchBarDto(Model model){
        model.addAttribute("search", new RechercheDto());
    }

    public Client getConnectedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        return clientService.findByUsername(currentUserName);
    }
}
