package soft_afric.clim.shop.clim_shop.web.dto.request;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FilterDto {
    private Long categorieID;
    private Long marqueID;
    private int budget;
}
