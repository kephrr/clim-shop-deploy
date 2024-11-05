package soft_afric.clim.shop.clim_shop.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ModePaiement {
    Liquide(0),
    OrangeMoney(1),
    Wave(2),
    Virement(3),
    Cheque(4);
    private final Integer index;
}
