package soft_afric.clim.shop.clim_shop.data.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name="categories")
public class Categorie extends AbstractEntity{
    @Column(unique = true, nullable = false, length = 50)
    private String libelle;

    @OneToMany(mappedBy = "categorie")
    private List<Clim> clims;
}
