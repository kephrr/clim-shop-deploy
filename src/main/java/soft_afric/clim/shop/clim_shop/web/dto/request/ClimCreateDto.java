package soft_afric.clim.shop.clim_shop.web.dto.request;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.*;
import soft_afric.clim.shop.clim_shop.data.entities.Categorie;
import soft_afric.clim.shop.clim_shop.data.entities.Clim;
import soft_afric.clim.shop.clim_shop.data.entities.Marque;
import soft_afric.clim.shop.clim_shop.data.enums.EtatClim;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClimCreateDto {
    private Long id;
    private String libelle;
    private String promotion;
    private String prix;
    private String image;
    private String capacite;
    private String qteStock;
    private String surface;
    private int etat;
    private Long marque;
    private Long categorie;

    public static Clim ToEntity(ClimCreateDto dto){
        String promotion = dto.getPromotion();
        String prix = dto.getPrix();
        String capacite = dto.getCapacite();
        String qteStock = dto.getQteStock();
        String surface = dto.getSurface();
        return Clim.builder()
                .libelle(dto.getLibelle())
                .promotion(Objects.equals(promotion, "") ?0: Integer.parseInt(promotion))
                .prix(Objects.equals(prix, "") ?0: Integer.parseInt(prix))
                .image(dto.getImage())
                .capacite(Objects.equals(capacite, "") ?0: Integer.parseInt(capacite))
                .qteStock(Objects.equals(qteStock, "") ?0: Integer.parseInt(qteStock))
                .surface(Objects.equals(surface, "") ?0:Double.parseDouble(surface))
                .etat(EtatClim.values()[dto.getEtat()])
                .build();
    }

    public static ClimCreateDto toDto(Clim c){
        return ClimCreateDto.builder()
                .id(c.getId())
                .libelle(c.getLibelle())
                .image(String.valueOf(c.getImage()))
                .prix(String.valueOf(c.getPrix()))
                .promotion(String.valueOf(c.getPromotion()))
                .capacite(String.valueOf(c.getCapacite()))
                .qteStock(String.valueOf(c.getQteStock()))
                .surface(String.valueOf(c.getSurface()))
                .etat(c.getEtat().getIndex())
                .marque(c.getMarque().getId())
                .marque(c.getCategorie().getId())
                .build();

        /*
        private Long marque;
        private Long categorie;
        * */
    }
}
