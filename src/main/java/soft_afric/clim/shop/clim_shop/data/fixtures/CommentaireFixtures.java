package soft_afric.clim.shop.clim_shop.data.fixtures;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import soft_afric.clim.shop.clim_shop.data.entities.Client;
import soft_afric.clim.shop.clim_shop.data.entities.Commentaire;
import soft_afric.clim.shop.clim_shop.data.repositories.ActionRepository;
import soft_afric.clim.shop.clim_shop.data.repositories.ClientRepository;
import soft_afric.clim.shop.clim_shop.data.repositories.CommentaireRepository;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//@Component
@Order(7)
@RequiredArgsConstructor
public class CommentaireFixtures implements CommandLineRunner {
    private final CommentaireRepository commentaireRepository;
    private final ClientRepository clientRepository;
    @Override
    public void run(String... args) throws Exception {
        String[] comments = {"J'ai adoré le service client. Vraiment à l'écoute, efficace et compréhensif. " +
                "\nJe recommande vivement !!",
                "Ca s'est très bien passé, de la commande à l'installation."};
        String[] titles = {"Avis service client",
                "Retour d'expérience"};
        List<Client> all = clientRepository.findAll();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String dateString = formatter.format(date);
        Date parsedDate = formatter.parse(dateString);

        for (int i=0; i < all.size(); i++) {
            Commentaire comment = Commentaire.builder()
                    .title(titles[i])
                    .content(comments[i])
                    .date(parsedDate)
                    .build();
            comment.setClient(all.get(i));
            commentaireRepository.save(comment);
        }
    }
}
