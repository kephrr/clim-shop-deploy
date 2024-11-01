package soft_afric.clim.shop.clim_shop.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EtatPaiement {
    Payer(0),
    NonPayer(1);
    private final Integer index;
}

