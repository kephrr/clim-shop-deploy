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
    private Double surface;
    private int prix;
    private int prixFinal;
    private int capacite;

    private String libelle;
    private String ref;
    private String marque;
    private String image;
    private String etat;
    private String promotion;
    private String categorie;
    private String type;
    private String guarantie;
    private String specs;


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
                    .ref(clim.getReference())
                    .type(clim.getType().getLibelle())
                    .categorie(clim.getCategorie().getLibelle())
                    .marque(clim.getMarque().getLibelle())
                    .specs(clim.getSpecs())
                    .prix(clim.getPrix())
                    .prixFinal(prixFinal)
                    .guarantie(parseGuaranty(clim.getGarantie()))
                    .image(clim.getImage())
                    .etat(clim.getEtat().toString())
                    .surface(clim.getSurface())
                    .promotion(promo==0?"Aucune":"-"+promo+"%")
                    .capacite(clim.getCapacite()).build();
    }

    public static String parseGuaranty(int days){
        String result;
        if(days>=365){
            result =  days/365 + " an(s)";
            if(days%365 != 0) result += " et "+days%365+" jour(s)";
        }else if(days>=30){
            result = days/30+" mois";
            if(days%30 != 0) result += " et "+days%30+" jour(s)";
        }else{
            result = days + " jour(s)";
        }
        return result;
    }
}
