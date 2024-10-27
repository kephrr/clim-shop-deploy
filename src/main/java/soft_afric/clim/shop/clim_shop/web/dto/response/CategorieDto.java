package soft_afric.clim.shop.clim_shop.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import soft_afric.clim.shop.clim_shop.data.entities.Categorie;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategorieDto {
    private Long id;
    private String libelle;

    public static CategorieDto toDto(Categorie categorie) {
        return CategorieDto.builder()
                .id(categorie.getId())
                .libelle(categorie.getLibelle())
                .build();
    }
}
