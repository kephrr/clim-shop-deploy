package soft_afric.clim.shop.clim_shop.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import soft_afric.clim.shop.clim_shop.data.entities.Client;
import soft_afric.clim.shop.clim_shop.data.entities.Clim;
import soft_afric.clim.shop.clim_shop.data.entities.Commande;
import soft_afric.clim.shop.clim_shop.data.enums.EtatCommande;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class DashBoardDto {
    private int commandesEnCours;
    private int chiffreAffaireMensuel;
    private int clientsActifs;
    private int stockClims;
    private String TauxCA;
    private String TauxCommande;

    public static DashBoardDto toDto(List<Commande> commandes, List<Client> clients, List<Clim> clims) {
        int commandesEnCours = 0;
        int chiffreAffaireMensuel = 0;
        int chiffreAffaireDernierMensuel = 0;

        // Créer une instance de Calendar pour obtenir le mois et l'année actuels
        Calendar calendar = Calendar.getInstance();
        int moisActuel = calendar.get(Calendar.MONTH);
        int anneeActuelle = calendar.get(Calendar.YEAR);


        calendar.add(Calendar.MONTH, -1); // Soustraire un mois
        // Récupérer les informations du mois précédent
        int moisDernier = calendar.get(Calendar.MONTH) + 1; // Les mois sont indexés à partir de 0
        int annee = calendar.get(Calendar.YEAR);

        int commandesMensuelles = 0;
        int commandesDernierMensuelles = 0;
        for (Commande c : commandes) {
            // Créer une instance de Calendar pour la date de la commande
            calendar.setTime(c.getDateCommmande());
            int moisCommande = calendar.get(Calendar.MONTH);
            int anneeCommande = calendar.get(Calendar.YEAR);

            // Vérifier si le mois et l'année de la commande correspondent au mois et à l'année actuels
            if (moisCommande == moisActuel && anneeCommande == anneeActuelle) {
                chiffreAffaireMensuel += c.getMontantFinal();
                commandesMensuelles += 1;
            }
            if (moisCommande == moisDernier && anneeCommande == annee) {
                chiffreAffaireDernierMensuel += c.getMontantFinal();
                commandesDernierMensuelles += 1;
            }

            // Commandes en cours
            if(c.getEtatCommande()== EtatCommande.Encour) commandesEnCours+=1;
        }



        // Stock de clims
        int stockClims = 0;
        for(Clim cl : clims) {
            stockClims += cl.getQteStock();
        }

        // Clients Actifs
        int clientsActifs = clients.size();

        float tauxCA = (float) (chiffreAffaireMensuel - chiffreAffaireDernierMensuel) / (chiffreAffaireDernierMensuel * 100);

        float tauxCommandes = (float) (commandesMensuelles - commandesDernierMensuelles) / (commandesDernierMensuelles * 100);

        return DashBoardDto.builder()
                .commandesEnCours(commandesEnCours)
                .chiffreAffaireMensuel(chiffreAffaireMensuel)
                .clientsActifs(clientsActifs)
                .stockClims(stockClims)
                .TauxCommande(getInd(tauxCommandes)+tauxCA+" %")
                .TauxCA(getInd(tauxCA)+tauxCA+" %")
                .build();
    }

    public static String getInd(float val){
        if(0>val) {
            return "-";
        }
        return "+";

    }
}
