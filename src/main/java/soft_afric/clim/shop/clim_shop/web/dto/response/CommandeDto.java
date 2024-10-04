package soft_afric.clim.shop.clim_shop.web.dto.response;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import soft_afric.clim.shop.clim_shop.data.entities.Client;
import soft_afric.clim.shop.clim_shop.data.entities.Commande;
import soft_afric.clim.shop.clim_shop.data.entities.LigneCommande;
import soft_afric.clim.shop.clim_shop.data.enums.EtatCommande;
import soft_afric.clim.shop.clim_shop.data.enums.ModePaiement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CommandeDto {
    private String client;
    private String adresse;
    private String date;
    private ModePaiement modePaiement;
    private EtatCommande etatCommande;
    private  int montant;
    private List<String> ligneCommandes;

    public static CommandeDto toDto(Commande c) {
        List<String> ligneCommandes = new ArrayList<>();
        for(LigneCommande ligne : c.getLigneCommandes()){
            ligneCommandes.add(ligne.toString());
        }
        return CommandeDto.builder()
                .client(c.getClient().getNomComplet())
                .adresse(c.getClient().getAdresse().toString())
                .date(c.getDateCommmande().toString())
                .modePaiement(c.getModePaiement())
                .etatCommande(c.getEtatCommande())
                .montant(c.getMontant())
                .ligneCommandes(ligneCommandes)
                .build();
    }
}
