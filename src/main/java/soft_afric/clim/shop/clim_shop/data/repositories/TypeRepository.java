package soft_afric.clim.shop.clim_shop.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import soft_afric.clim.shop.clim_shop.data.entities.Type;

import java.util.Optional;

public interface TypeRepository extends JpaRepository<Type, Long> {
    Optional<Type> findByLibelleAndIsActivedTrue(String libelle);
}
