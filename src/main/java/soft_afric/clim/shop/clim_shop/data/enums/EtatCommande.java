package soft_afric.clim.shop.clim_shop.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EtatCommande {
    Encour(0),
    Terminer(1),
    Facturer(2),
    Payer(3);
    private final Integer index;
}
