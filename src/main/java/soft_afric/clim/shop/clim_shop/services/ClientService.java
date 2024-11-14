package soft_afric.clim.shop.clim_shop.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import soft_afric.clim.shop.clim_shop.data.entities.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService extends IService<Client,Long> {
    Client findByUsername(String username);
    Optional<Client> findByNumTel(String numTel);
    Optional<Client> findByNumero(int numero);
    int getClientsCount();
    List<Client> findAll(String tel);
}
