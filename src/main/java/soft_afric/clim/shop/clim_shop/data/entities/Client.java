package soft_afric.clim.shop.clim_shop.data.entities;



import jakarta.persistence.*;
import lombok.*;
import soft_afric.clim.shop.clim_shop.security.data.entities.AppUser;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "clients")
@Builder
@DiscriminatorValue(value="client")
public class Client extends AppUser {
    private int numero;
    @Column(length = 50)
    private String nomComplet;
    @Column(length = 20)
    private  String tel;
    @Embedded
    private Adresse adresse;
    private String commentaire;
    @OneToMany(mappedBy = "client")
    private List<Commande> commandes;
    @OneToMany(mappedBy = "client")
    private List<Action> actions;
    @OneToMany(mappedBy = "client")
    private List<Commentaire> commentaires;

    @Override
    public String toString(){
        return nomComplet;
    }

    public void addCommande(Commande commande){
        this.commandes.add(commande);
    }
}
