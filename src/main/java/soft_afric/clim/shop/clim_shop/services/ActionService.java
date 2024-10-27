package soft_afric.clim.shop.clim_shop.services;

import soft_afric.clim.shop.clim_shop.data.entities.Action;
import soft_afric.clim.shop.clim_shop.data.entities.Commentaire;
import soft_afric.clim.shop.clim_shop.data.enums.ActionType;

import java.util.List;

public interface ActionService extends IService<Action, Long> {
    List<Action> findByCommentAndType(Long id, ActionType type);
}
