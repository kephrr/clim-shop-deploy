package soft_afric.clim.shop.clim_shop.security.data.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import soft_afric.clim.shop.clim_shop.security.data.entities.AppRole;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRoleName(String roleName);
}
