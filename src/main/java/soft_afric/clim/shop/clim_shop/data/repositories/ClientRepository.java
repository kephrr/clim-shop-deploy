package soft_afric.clim.shop.clim_shop.data.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import soft_afric.clim.shop.clim_shop.data.entities.Client;

import java.util.List;
import java.util.Optional;


public interface ClientRepository extends JpaRepository<Client,Long> {
    Client findClientByNomCompletAndIsActivedTrue(String nomComplet);
    Optional<Client> findByTelAndIsActivedTrue(String tel);
    Optional<Client> findByNumeroAndIsActivedTrue(int numero);
    int countByIsActived(Boolean active);

    @Query("SELECT DISTINCT cl FROM Client cl "+
            "WHERE cl.isActived = true "+
            "AND (:tel IS NULL OR cl.tel LIKE %:tel%) "
    )
    List<Client> findAllByTelephone(@Param("tel") String tel);
}