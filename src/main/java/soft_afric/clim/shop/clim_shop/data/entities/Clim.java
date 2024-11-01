package soft_afric.clim.shop.clim_shop.data.entities;

import jakarta.persistence.*;
import lombok.*;
import soft_afric.clim.shop.clim_shop.data.enums.EtatClim;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name="clims")
public class Clim extends AbstractEntity{
    @Column(unique = true, nullable = false, length = 100)
    private String libelle;

    @Column(unique = true, length = 100)
    private String reference;

    private String specs;
    private String image;
    private int prix;
    private int capacite;
    private int qteStock;
    private int garantie=365;
    private Double surface;

    @Column(length = 3)
    private int promotion;
    private EtatClim etat;

    @ManyToOne
    private Marque marque;

    @ManyToOne
    private Categorie categorie;

    @ManyToOne
    private Type type;

    @ManyToOne
    private Fournisseur fournisseur;

    @Override
    public String toString() {
        return marque.getLibelle()+" "+libelle;
    }
}
