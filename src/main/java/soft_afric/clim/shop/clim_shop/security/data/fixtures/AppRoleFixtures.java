package soft_afric.clim.shop.clim_shop.security.data.fixtures;


import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import soft_afric.clim.shop.clim_shop.security.services.SecurityService;

//@Component
@Order(3)
@RequiredArgsConstructor
public class AppRoleFixtures implements CommandLineRunner {
    private  final SecurityService service;


    @Override
    public void run(String... args) throws Exception {
        service.addRole("Client");
        service.addRole("Admin");

    }
}
