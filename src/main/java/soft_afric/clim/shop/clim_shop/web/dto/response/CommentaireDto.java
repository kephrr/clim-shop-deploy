package soft_afric.clim.shop.clim_shop.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import soft_afric.clim.shop.clim_shop.data.entities.Action;
import soft_afric.clim.shop.clim_shop.data.entities.Commentaire;
import soft_afric.clim.shop.clim_shop.data.enums.ActionType;

import java.util.Date;
import java.util.List;


@AllArgsConstructor
@Getter
@Setter
@Builder
public class CommentaireDto {
    private String title;
    private String content;
    private String client;
    private String date;
    private int likes;
    private int dislikes;
    private int shares;

    public static CommentaireDto toDto(Commentaire commentaire) {
        return CommentaireDto.builder()
                .title(commentaire.getTitle())
                .content(commentaire.getContent())
                .date(commentaire.getDate().toString())
                .likes((int) commentaire.getActions() .stream()
                        .filter(c -> c.getType()==ActionType.Like)
                        .count())
                .dislikes((int) commentaire.getActions() .stream()
                        .filter(c -> c.getType()==ActionType.Dislike)
                        .count())
                .shares((int) commentaire.getActions() .stream()
                        .filter(c -> c.getType()==ActionType.Share)
                        .count())
                .client(commentaire.getClient().getNomComplet())
                .build();
    }
}
