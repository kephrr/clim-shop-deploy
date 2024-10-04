package soft_afric.clim.shop.clim_shop.data.fixtures;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import soft_afric.clim.shop.clim_shop.data.entities.Adresse;
import soft_afric.clim.shop.clim_shop.data.entities.Client;
import soft_afric.clim.shop.clim_shop.data.entities.Commande;
import soft_afric.clim.shop.clim_shop.data.entities.LigneCommande;
import soft_afric.clim.shop.clim_shop.data.enums.EtatCommande;
import soft_afric.clim.shop.clim_shop.data.enums.ModePaiement;
import soft_afric.clim.shop.clim_shop.data.repositories.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Component
@Order(6)
@RequiredArgsConstructor
public class CommandeFixtures implements CommandLineRunner {
    private final CommandeRepository commandeRepository;
    private  final ClimRepository climRepository;
    private  final LigneCommandeRepository ligneCommandeRepository;
    private  final ClientRepository clientRepository;
    @Override
    public void run(String... args) throws Exception {
        for (int i=0;i<4;i++){
            Client client = clientRepository.getReferenceById(4L);
            Commande commande = new Commande();
            commande.setClient(client);

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            String dateString = formatter.format(date);
            Date parsedDate = formatter.parse(dateString);

            commande.setEtatCommande(i%2==0? EtatCommande.Encour: EtatCommande.Terminer);
            commande.setDateCommmande(parsedDate);
            commande.setIsActived(true);
            commande.setModePaiement(ModePaiement.values()[i%3==0?1:i%2==0?0:1]);
            int montant = 0;

            commande.setClient(client);

            commandeRepository.save(commande);
            for (int j=1;j<5;j++){
                LigneCommande ligneCommande = new LigneCommande();
                ligneCommande.setQuantite(2*i+1);
                ligneCommande.setMontant(500*i+100);
                ligneCommande.setPrix(500*i+100);
                montant+=ligneCommande.getMontant();
                ligneCommande.setClim(climRepository.getReferenceById((long)j));
                ligneCommande.setCommande(commande);

                ligneCommande.setCommande(commande);
                ligneCommandeRepository.save(ligneCommande);
            }
            commande.setMontant(montant);

            commandeRepository.save(commande);
            //clientRepository.save(client);

        }
    }
}
