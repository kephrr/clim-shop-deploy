package soft_afric.clim.shop.clim_shop.services;

import soft_afric.clim.shop.clim_shop.data.entities.Type;

import java.util.Optional;

public interface TypeService extends IService<Type, Long> {
    Optional<Type> findByLiblle(String libelle);
}
