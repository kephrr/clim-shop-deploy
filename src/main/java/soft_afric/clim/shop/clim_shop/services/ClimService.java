package soft_afric.clim.shop.clim_shop.services;

import soft_afric.clim.shop.clim_shop.data.entities.Categorie;
import soft_afric.clim.shop.clim_shop.data.entities.Clim;
import soft_afric.clim.shop.clim_shop.data.entities.Marque;

import java.util.List;

public interface ClimService extends IService<Clim, Long> {
    List<Clim> findAllPromotedClims();
    List<Clim> findAllByCategorieAndMarqueAndBudget(Categorie categorie, Marque marque, int budget);
    List<Clim> findAllBySearchedKEyword(String keyword);
}
