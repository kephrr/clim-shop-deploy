package soft_afric.clim.shop.clim_shop.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import soft_afric.clim.shop.clim_shop.data.entities.Marque;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class MarqueDto {
    private Long id;
    private String libelle;

    public static MarqueDto toDto(Marque marque) {
        return MarqueDto.builder()
                .id(marque.getId())
                .libelle(marque.getLibelle())
                .build();
    }
}
