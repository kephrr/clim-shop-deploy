package soft_afric.clim.shop.clim_shop.data.fixtures;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import soft_afric.clim.shop.clim_shop.data.entities.*;
import soft_afric.clim.shop.clim_shop.data.enums.EtatClim;
import soft_afric.clim.shop.clim_shop.data.repositories.*;

import java.util.List;

//@Component
@Order(1)
@RequiredArgsConstructor
public class ClimFixtures implements CommandLineRunner {
    private final CategorieRepository categorieRepository;
    private final ClimRepository climRepository;
    private final MarqueRepository marqueRepository;
    private final TypeRepository typeRepository;
    private final FournisseurRepository fournisseurRepository;
    @Override
    public void run(String... args) throws Exception {
        String[] types = {"Clim","Split"};
        String[] categories = {"Mural/fixe","Cassette","Gainable","mobile"};
        String[] marques = {"LG","Lenovo","Samsung","Toshiba","Panasonic"};
            for (String t : types) {
                typeRepository.save(Type.builder().libelle(t).build());
            }
            for (String cat : categories) {
                categorieRepository.save(Categorie.builder().libelle(cat).build());
            }
            for (String marque : marques) {
                marqueRepository.save(Marque.builder().libelle(marque).build());
            }
            for (int i = 1; i <= 25; i++) {
                Marque marque = marqueRepository.findById(i%2==0?1L:3L).orElse(Marque.builder().libelle("Autre").build());
                Fournisseur fournisseur = fournisseurRepository.findById(i%3==0?1L:i%2==0?2L:3L).orElseThrow(()-> new RuntimeException("No fournisseur was found"));
                climRepository.save(
                        Clim.builder()
                                .libelle(i%3==0?"Split Pro "+i:"Clim New Gen V"+i)
                                .reference("SP"+marque.getLibelle().toUpperCase().charAt(0)+i)
                                .marque(marque)
                                .garantie(i%3==0?400:365)
                                .categorie(categorieRepository.findById(i%2==0?1L:4L).orElse(Categorie.builder().libelle("Autre").build()))
                                .capacite(i*1000)
                                .surface(1000.0+i*100.0)
                                .specs(i%2==0?"Auto lavant":"Silencieux")
                                .type(typeRepository.findById(i%2==0?1L:2L).orElse(Type.builder().libelle("Autre").build()))
                                .etat(i%2==0?EtatClim.Correct:i%3==0?EtatClim.Bon:EtatClim.Excellent)
                                .prix(200000+i*10000)
                                .qteStock(i%3==0?10:5)
                                .promotion(i%3==0?20:i%2==0?0:10)
                                .fournisseur(fournisseur)
                                .image(i%2==0?"/img/images (1).jpg":"/img/1-11.png")
                                .build()
                );
            }
    }

}
