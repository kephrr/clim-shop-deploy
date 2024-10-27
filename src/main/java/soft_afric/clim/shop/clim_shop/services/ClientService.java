package soft_afric.clim.shop.clim_shop.services;

import soft_afric.clim.shop.clim_shop.data.entities.Client;

import java.util.Optional;

public interface ClientService extends IService<Client,Long> {
    Client findByUsername(String username);
    Optional<Client> findByNumTel(String numTel);
}
