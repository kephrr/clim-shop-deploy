package soft_afric.clim.shop.clim_shop.data.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import soft_afric.clim.shop.clim_shop.data.enums.EtatCommande;
import soft_afric.clim.shop.clim_shop.data.enums.ModePaiement;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name="commande")
public class Commande extends AbstractEntity{
    private ModePaiement modePaiement;
    @Enumerated(value = EnumType.STRING)
    private EtatCommande etatCommande;
    @ManyToOne
    Client client;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateCommmande;
    private  int montant;
    @OneToMany(mappedBy = "commande")
    private List<LigneCommande> ligneCommandes;
}
