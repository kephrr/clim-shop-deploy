package soft_afric.clim.shop.clim_shop.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import soft_afric.clim.shop.clim_shop.data.entities.Commentaire;
import soft_afric.clim.shop.clim_shop.data.enums.ActionType;

import java.util.List;

public interface CommentaireRepository extends JpaRepository<Commentaire, Long> {

    @Query( "SELECT c.id, c.title, c.content, c.date, COUNT(a.type) AS like_count "+
            "FROM Commentaire c "+
            "JOIN Action a ON c = a.commentaire "+
            "WHERE a.type = :type "+
            "GROUP BY c.id, c.title, c.content, c.date "+
            "ORDER BY like_count DESC "+
            "LIMIT 3")
    List<Commentaire> findThreeBetterComments(@Param("type") ActionType type);
}
