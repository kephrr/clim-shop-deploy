package soft_afric.clim.shop.clim_shop.data.entities;


import jakarta.persistence.Embeddable;
import lombok.*;


@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Adresse {
    private String quartier;
    private String ville;
    private String numVilla;
    @Override
    public String  toString(){
        return quartier+ " | "+ ville +" | "+numVilla;
    }
}
