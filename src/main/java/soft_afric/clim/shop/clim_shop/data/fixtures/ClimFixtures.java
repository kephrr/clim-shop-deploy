package soft_afric.clim.shop.clim_shop.data.fixtures;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import soft_afric.clim.shop.clim_shop.data.entities.Categorie;
import soft_afric.clim.shop.clim_shop.data.entities.Clim;
import soft_afric.clim.shop.clim_shop.data.entities.Marque;
import soft_afric.clim.shop.clim_shop.data.enums.EtatClim;
import soft_afric.clim.shop.clim_shop.data.repositories.CategorieRepository;
import soft_afric.clim.shop.clim_shop.data.repositories.ClimRepository;
import soft_afric.clim.shop.clim_shop.data.repositories.MarqueRepository;

//@Component
@Order(0)
@RequiredArgsConstructor
public class ClimFixtures implements CommandLineRunner {
    private final CategorieRepository categorieRepository;
    private final ClimRepository climRepository;
    private final MarqueRepository marqueRepository;
    @Override
    public void run(String... args) throws Exception {
        String[] types = {"Mural/fixe","Cassette","Gainable","mobile"};
        String[] marques = {"LG","Lenovo","Samsung","Toshiba","Panasonic"};

            for (String type : types) {
                categorieRepository.save(Categorie.builder().libelle(type).build());
            }
            for (String marque : marques) {
                marqueRepository.save(Marque.builder().libelle(marque).build());
            }
            for (int i = 1; i <= 25; i++) {
                climRepository.save(
                        Clim.builder()
                                .libelle(i%3==0?"Split Pro "+i:"Clim New Gen V"+i)
                                .marque(marqueRepository.findById(i%2==0?1L:3L).orElse(Marque.builder().libelle("Autre").build()))
                                .categorie(categorieRepository.findById(i%2==0?1L:4L).orElse(Categorie.builder().libelle("Autre").build()))
                                .capacite(i*1000)
                                .surface(1000.0+i*100.0)
                                .etat(i%2==0?EtatClim.Correct:i%3==0?EtatClim.Bon:EtatClim.Excellent)
                                .prix(200000+i*10000)
                                .qteStock(i%3==0?10:5)
                                .promotion(i%3==0?20:i%2==0?0:10)
                                .image(i%2==0?"/img/images (1).jpg":"/img/1-11.png")
                                .build()
                );
            }
    }

}
