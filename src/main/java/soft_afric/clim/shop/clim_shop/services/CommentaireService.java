package soft_afric.clim.shop.clim_shop.services;

import soft_afric.clim.shop.clim_shop.data.entities.Commentaire;

import java.util.List;

public interface CommentaireService extends IService<Commentaire, Long> {
    List<Commentaire> findFourthBetterComments();
}
