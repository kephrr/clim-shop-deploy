package soft_afric.clim.shop.clim_shop.services;

import soft_afric.clim.shop.clim_shop.data.entities.Client;

public interface ClientService extends IService<Client,Long> {
    Client findByUsername(String username);
}
