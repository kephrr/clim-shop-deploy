package soft_afric.clim.shop.clim_shop.data.fixtures;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import soft_afric.clim.shop.clim_shop.data.entities.Adresse;
import soft_afric.clim.shop.clim_shop.data.entities.Client;
import soft_afric.clim.shop.clim_shop.data.repositories.ClientRepository;
import soft_afric.clim.shop.clim_shop.security.services.SecurityService;

//@Component
@Order(5)
@RequiredArgsConstructor
public class ClientFixtures implements CommandLineRunner {

    private final ClientRepository clientRepository;
    private  final PasswordEncoder passwordEncoder;
    private  final SecurityService service;
    @Override
    public void run(String... args) throws Exception {
        for (int i = 0; i <2 ; i++) {
            Client client = new Client();
            client.setNomComplet(i%2==0?"Utilisateur":"Abdoulaye SY");
            client.setTel(i%2==0?"776693020":"789990001");
            client.setAdresse(
                    Adresse.builder()
                            .numVilla("villa 200")
                            .quartier(i%2==0?"Foire":"Ouakam")
                            .ville(i%2==0?"Dakar":"Saint-Louis")
                            .build()
            );
            client.setIsActived(true);
            client.setPassword(passwordEncoder.encode("passer"));
            client.setLogin(client.getNomComplet());
            clientRepository.save(client);
            System.out.println("voici : "+client.getLogin());
            service.addRoleToUser(i%2==0?"Utilisateur":"Abdoulaye SY","Client");
        }
    }
}
