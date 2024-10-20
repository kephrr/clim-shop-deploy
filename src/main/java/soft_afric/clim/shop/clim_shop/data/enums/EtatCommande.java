package soft_afric.clim.shop.clim_shop.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EtatCommande {
    Encour(0),
    Livree(1),
    Archiver(2),
    Annuler(3);
    private final Integer index;
}
