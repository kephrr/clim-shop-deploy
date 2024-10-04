package soft_afric.clim.shop.clim_shop.data.entities;

import jakarta.persistence.*;
import lombok.*;
import soft_afric.clim.shop.clim_shop.data.enums.EtatClim;

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
    private int prix;
    private String image;
    private int capacite;
    private int qteStock;
    private Double surface;
    @Column(length = 3)
    private int promotion;
    private EtatClim etat;
    @ManyToOne
    private Marque marque;
    @ManyToOne
    private Categorie categorie;

}
