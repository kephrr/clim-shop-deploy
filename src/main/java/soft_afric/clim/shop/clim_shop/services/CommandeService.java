package soft_afric.clim.shop.clim_shop.services;

import soft_afric.clim.shop.clim_shop.data.entities.Client;
import soft_afric.clim.shop.clim_shop.data.entities.Commande;
import soft_afric.clim.shop.clim_shop.web.dto.request.PanierRequestDto;

import java.text.ParseException;
import java.util.List;

public interface CommandeService extends IService<Commande, Long> {
    List<Commande> findAll(Client client);
}
