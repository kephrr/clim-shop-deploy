package soft_afric.clim.shop.clim_shop.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import soft_afric.clim.shop.clim_shop.data.entities.Clim;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClimDto {
    private Long id;
    private String libelle;
    private String marque;
    private int prix;
    private int prixFinal;
    private String image;
    private String etat;
    private Double surface;
    private int capacite;
    private String promotion;
    private String categorie;

    public static ClimDto toCardDto(Clim clim){
        int promo = clim.getPromotion();
        return ClimDto.builder()
                    .id(clim.getId())
                    .libelle(clim.getLibelle())
                    .marque(clim.getMarque().getLibelle())
                    .prix(clim.getPrix())
                    .promotion(promo==0?"":"-"+promo+"%")
                    .image(clim.getImage()).build();
    }

    public static ClimDto toDetailsDto(Clim clim){
        int promo = clim.getPromotion();
        int prixInit = clim.getPrix();
        int prixFinal = prixInit - (prixInit*promo)/100;
        return ClimDto.builder()
                    .id(clim.getId())
                    .libelle(clim.getLibelle())
                    .categorie(clim.getCategorie().getLibelle())
                    .marque(clim.getMarque().getLibelle())
                    .prix(clim.getPrix())
                    .prixFinal(prixFinal)
                    .image(clim.getImage())
                    .etat(clim.getEtat().toString())
                    .surface(clim.getSurface())
                    .promotion(promo==0?"Aucune":"-"+promo+"%")
                    .capacite(clim.getCapacite()).build();
    }
}
