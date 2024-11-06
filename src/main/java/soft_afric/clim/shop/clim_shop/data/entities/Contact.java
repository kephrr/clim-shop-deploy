package soft_afric.clim.shop.clim_shop.data.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name="contact")
public class Contact extends AbstractEntity{
    private String nom;
    private String email;
    @Column(unique=true)
    private String telephone;

    @Override
    public String toString() {
        return nom + " | " + email + " | "+ telephone;
    }

    @ManyToOne
    private Fournisseur fournisseur;
}
