package soft_afric.clim.shop.clim_shop.security.data.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import soft_afric.clim.shop.clim_shop.security.data.entities.AppUser;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {

    AppUser findByLogin(String login);
}
