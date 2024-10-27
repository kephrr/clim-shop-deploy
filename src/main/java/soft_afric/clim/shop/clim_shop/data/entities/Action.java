package soft_afric.clim.shop.clim_shop.data.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import soft_afric.clim.shop.clim_shop.data.enums.ActionType;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name="action")
public class Action extends AbstractEntity{
    private ActionType type;
    @ManyToOne
    Commentaire commentaire;
    @ManyToOne
    Client client;
}