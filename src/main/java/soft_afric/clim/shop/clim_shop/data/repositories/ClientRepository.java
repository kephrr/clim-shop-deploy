package soft_afric.clim.shop.clim_shop.data.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import soft_afric.clim.shop.clim_shop.data.entities.Client;

import java.util.List;


public interface ClientRepository extends JpaRepository<Client,Long> {
    List<Client> findByIsActived(Boolean active);
    Page<Client> findAllByIsActivedTrue(Pageable pageable);
    List<Client> findByIsActivedTrue();
    Client findClientByNomCompletAndIsActivedTrue(String nomComplet);
}