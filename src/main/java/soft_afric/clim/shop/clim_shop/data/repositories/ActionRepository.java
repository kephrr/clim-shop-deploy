package soft_afric.clim.shop.clim_shop.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import soft_afric.clim.shop.clim_shop.data.entities.Action;

import java.util.List;

public interface ActionRepository extends JpaRepository<Action, Long> {
    List<Action> findAllByIsActivedTrueAndCommentaire_Id(Long id);
}