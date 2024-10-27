package soft_afric.clim.shop.clim_shop.web.dto.response;

import lombok.*;
import soft_afric.clim.shop.clim_shop.data.enums.EtatClim;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EtatClimDto {
    private int id;
    private String libelle;

    public static EtatClimDto toDto(EtatClim etatClim) {
        return EtatClimDto.builder()
                .id(etatClim.getIndex())
                .libelle(etatClim.name())
                .build();
    }
}
