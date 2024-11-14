package soft_afric.clim.shop.clim_shop.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import soft_afric.clim.shop.clim_shop.data.entities.Categorie;
import soft_afric.clim.shop.clim_shop.data.entities.Client;
import soft_afric.clim.shop.clim_shop.data.entities.Clim;
import soft_afric.clim.shop.clim_shop.data.entities.Marque;

import java.util.List;

public interface ClimRepository extends JpaRepository<Clim, Long> {
    List<Clim> findAllByIsActivedTrueAndPromotionGreaterThan(int promo);

    @Query("SELECT DISTINCT c FROM Clim c "+
            "WHERE c.isActived = true "+
            "AND (:categorie IS NULL OR c.categorie = :categorie) "+
            "AND (:marque IS NULL OR c.marque = :marque) "+
            "AND (:budget = 0 OR c.prix < :budget) ")
    List<Clim> findAllByMarqueCategorieAndBudget(
             @Param("categorie") Categorie categorie,
             @Param("marque") Marque marque,
             @Param("budget") int budget);

    @Query("SELECT DISTINCT c FROM Clim  c "+
            "WHERE c.isActived = true "+
            "AND (:keyword IS NULL OR c.libelle LIKE %:keyword%) "+
            "AND (:keyword IS NULL OR c.marque.libelle LIKE %:keyword%) "+
            "AND (:keyword IS NULL OR c.categorie.libelle LIKE %:keyword%) ")
    List<Clim> findAllBySearchedKeyword(
            @Param("keyword") String keyword);

    List<Clim> findAllByIsActivedTrue();

    @Query("SELECT DISTINCT c FROM Clim  c "+
            "WHERE c.isActived = true "+
            "AND (:key IS NULL OR c.libelle LIKE %:key%) "+
            "AND (:key IS NULL OR c.marque.libelle LIKE %:key%) "+
            "AND (:key IS NULL OR c.categorie.libelle LIKE %:key%) ")
    List<Clim> findAll(
            @Param("key") String key);
}
