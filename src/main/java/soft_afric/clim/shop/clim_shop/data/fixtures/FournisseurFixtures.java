package soft_afric.clim.shop.clim_shop.data.fixtures;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import soft_afric.clim.shop.clim_shop.data.entities.Adresse;
import soft_afric.clim.shop.clim_shop.data.entities.Contact;
import soft_afric.clim.shop.clim_shop.data.entities.Fournisseur;
import soft_afric.clim.shop.clim_shop.data.enums.EtatEncours;
import soft_afric.clim.shop.clim_shop.data.enums.EtatPaiement;
import soft_afric.clim.shop.clim_shop.data.enums.ModePaiement;
import soft_afric.clim.shop.clim_shop.data.repositories.ContactRepository;
import soft_afric.clim.shop.clim_shop.data.repositories.FournisseurRepository;

import java.util.*;

//@Component
@Order(0)
@RequiredArgsConstructor
public class FournisseurFixtures implements CommandLineRunner {
    private final FournisseurRepository fournisseurRepository;
    private final ContactRepository contactRepository;
    @Override
    public void run(String... args) throws Exception {
        String[] societe={"DAIKIN", "LG Sénégal", "Samsung Sénégal"};
        String[] reference={"refDAIKIN", "LG Sénégal", "Samsung Sénégal"};
        String[] siret={"SRGJNDNEK1E", "DENZ7VFRGB", "183HNDVREV"};
        String[] divers={"", "", ""};
        String[] noms = {"Anne DIAGNE","Andre GUEYE","Kamara SY"};
        String[] emails = {"anned@gmail.com","andgueye.daikin@gmail.com","kamsy.samung@edu.sn"};
        String[] telephones = {"338972271","335397447","335111440"};

        for (int i = 0; i < 3; i++) {
            List<Contact> contacts = new ArrayList<>();
            Fournisseur fournisseur = Fournisseur.builder()
                    .societe(societe[i])
                    .reference(reference[i])
                    .siret(siret[i])
                    .divers(divers[i])
                    .modePaiement(ModePaiement.values()[i%3])
                    .etatEncours(EtatEncours.values()[i%2])
                    .etatPaiement(EtatPaiement.values()[i%2])
                    .adresse(new Adresse("Dakar","Mermoz","780"))
                    .build();

            contacts.add(Contact.builder()
                    .nom(noms[i])
                            .email(emails[i])
                            .telephone(telephones[i])
                            .build());

            fournisseurRepository.save(fournisseur);
            contacts.forEach(c-> c.setFournisseur(fournisseur));
            contactRepository.saveAll(contacts);
            fournisseur.setContacts(contacts);
            fournisseurRepository.save(fournisseur);
        }
    }
}
