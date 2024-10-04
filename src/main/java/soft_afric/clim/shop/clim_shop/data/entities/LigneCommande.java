package soft_afric.clim.shop.clim_shop.data.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name="ligne_commande")
public class LigneCommande extends AbstractEntity{
    private int quantite;
    private int montant;
    private int prix;
    @ManyToOne
    private Clim clim;
    @ManyToOne
    private Commande commande;
    @Override
    public String  toString(){
        return quantite+ " * "+ clim.getLibelle() +" ("+prix+")"+" Total : "+montant;
    }
}