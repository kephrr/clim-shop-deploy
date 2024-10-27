package soft_afric.clim.shop.clim_shop.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EtatClim {
    Correct(1),
    Bon(2),
    Excellent(3),
    Neuf(4);
    private final Integer index;
}
