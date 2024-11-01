package soft_afric.clim.shop.clim_shop.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import soft_afric.clim.shop.clim_shop.data.entities.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
