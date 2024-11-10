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
import java.util.Objects;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class CommandeDto {
    private Long id;
    private String client;
    private String numClient;
    private String adresse;

    private String date;
    private String datePaiement;
    private String dateInstallation;
    private String dateLivraison;

    private ModePaiement modePaiement;
    private EtatCommande etatCommande;
    private String isInstalled;

    private String refPaiement;
    private  int montant;
    private  int livraison;
    private  int installation;
    private  int montantFinal;
    private  int accInstallation;
    private List<String> ligneCommandes;

    public static CommandeDto toDto(Commande c) {
        List<String> ligneCommandes = new ArrayList<>();
        for(LigneCommande ligne : c.getLigneCommandes()){
            ligneCommandes.add(ligne.toString());
        }
        return CommandeDto.builder()
                .id(c.getId())
                .client(c.getClient().getNomComplet())
                .adresse(c.getClient().getAdresse().toString())
                .date(c.getDateCommmande().toString())
                .modePaiement(c.getModePaiement())
                .etatCommande(c.getEtatCommande())
                .montant(c.getMontant())
                .ligneCommandes(ligneCommandes)
                .build();
    }

    public static CommandeDto toAdminDto(Commande c) {
        return CommandeDto.builder()
                .id(c.getId())
                .client(c.getClient().getNomComplet())
                .numClient(c.getClient().getTel())
                .adresse(c.getClient().getAdresse().toString())
                .date(c.getDateCommmande().toString())
                .etatCommande(c.getEtatCommande())
                .modePaiement(c.getModePaiement())
                .livraison(c.getLivraison())
                .montantFinal(c.getLivraison() + c.getInstallation() + c.getMontant())
                .build();
    }

    public static CommandeDto toAdminDetailsDto(Commande c) {
        List<String> ligneCommandes = new ArrayList<>();
        for(LigneCommande ligne : c.getLigneCommandes()){
            ligneCommandes.add(ligne.toString());
        }
        String ref = c.getRefPaiement();
        Date dateLivraison = c.getDateLivraison();
        Date dateInstallation = c.getDateInstallation();
        Date datePaiement = c.getDatePaiement();

        return CommandeDto.builder()
                .id(c.getId())
                .client(c.getClient().getNomComplet())
                .numClient(c.getClient().getTel())

                .adresse(c.getClient().getAdresse().toString())

                .refPaiement(Objects.equals(ref, "") ?"Non payé":ref)
                .date(c.getDateCommmande().toString())
                .datePaiement(datePaiement==null?"Non payé":c.getDatePaiement().toString())
                .dateInstallation(dateInstallation==null?"Non installé":c.getDateInstallation().toString())
                .dateLivraison(dateLivraison==null?"Non livré":c.getDateLivraison().toString())

                .modePaiement(c.getModePaiement())
                .etatCommande(c.getEtatCommande())
                .isInstalled(c.isInstalled()?"Installée(s)":"Non installée(s)")

                .montant(c.getMontant())
                .livraison(c.getLivraison())
                .installation(c.getInstallation())
                .accInstallation(c.getAccInstallation())
                .montantFinal(c.getLivraison() + c.getInstallation() + c.getMontant())

                .ligneCommandes(ligneCommandes)
                .build();
    }
}
