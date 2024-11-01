package soft_afric.clim.shop.clim_shop.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EtatEncours {
    EnCours(0),
    Achevee(1);
    private final Integer index;
}
