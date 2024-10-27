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
    private String refPaiement;
    private  int montant;
    private  int livraison;
    // private  int fraisLivraison;
    private int accInstallation;
    private  int installation;
    private  int montantFinal;
    private ModePaiement modePaiement;
    private boolean isInstalled;
    @ManyToOne
    Client client;

    @Enumerated(value = EnumType.STRING)
    private EtatCommande etatCommande;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateCommmande;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datePaiement;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateLivraison;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateInstallation;

    @OneToMany(mappedBy = "commande")
    private List<LigneCommande> ligneCommandes;
}
