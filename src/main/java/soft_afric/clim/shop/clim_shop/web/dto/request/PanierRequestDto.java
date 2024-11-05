package soft_afric.clim.shop.clim_shop.web.dto.request;

import lombok.*;
import soft_afric.clim.shop.clim_shop.web.dto.response.ClientDto;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class PanierRequestDto {
    private List<ClimPanierDto> articles;
    private int total = 0;
    private ClientDto client;
    private Boolean isFraisNotIncluded = false;
    private String dateInstallation;
    private int modePaiement;
    private int nbre=0;
    public void addClimToPanier(ClimPanierDto article){
        int pos = articles.indexOf(article);
        if(pos != -1){
            System.out.println("Article déjà pris");
            ClimPanierDto articleGetOfPanier =  articles.get(pos);
            articleGetOfPanier.calcQuantite(article.getQuantite());
            articleGetOfPanier.calcMontant(article.getQuantite()*articleGetOfPanier.getPrix());
            total+=article.getQuantite()*articleGetOfPanier.getPrix();
        }
        else{
            article.setMontant(article.getQuantite()*article.getPrix());
            articles.add(article);
            total+= article.getMontant();
        }
        resetNbre();
    }
    public void removeClimFromPanier(Long id){
        articles.removeIf(article -> article.getId().equals(id));
        resetNbre();
        resetTotal();
    }
    public void resetNbre(){
        nbre=articles.size();
    }

    public void resetTotal(){
        int count = 0;
        if (!isFraisNotIncluded) count = 30500;
        for (ClimPanierDto article : articles) {
            count += article.getMontant();
        }
        total = count;
    }
}
