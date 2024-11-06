package soft_afric.clim.shop.clim_shop.data.entities;

import jakarta.persistence.*;
import lombok.*;
import soft_afric.clim.shop.clim_shop.data.enums.EtatEncours;
import soft_afric.clim.shop.clim_shop.data.enums.EtatPaiement;
import soft_afric.clim.shop.clim_shop.data.enums.ModePaiement;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name="fournisseur")
public class Fournisseur extends AbstractEntity{
    @Column(unique=true)
    private String societe;
    @Column(unique=true)
    private String reference;
    @Column(unique=true)
    private String siret;
    private String divers;
    private ModePaiement modePaiement;
    private EtatPaiement etatPaiement;
    private EtatEncours etatEncours;

    @OneToOne
    private  Commentaire commentaire;
    @Embedded
    private Adresse adresse;

    @OneToMany(mappedBy = "fournisseur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contact> contacts;

    @OneToMany(mappedBy = "fournisseur")
    private List<Clim> clims;
}
