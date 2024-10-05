package soft_afric.clim.shop.clim_shop.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import soft_afric.clim.shop.clim_shop.data.entities.Client;
import soft_afric.clim.shop.clim_shop.data.entities.Commande;

import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {
    List<Commande> findAllByClient(Client client);
}
