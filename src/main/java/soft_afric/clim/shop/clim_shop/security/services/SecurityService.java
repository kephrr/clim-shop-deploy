package soft_afric.clim.shop.clim_shop.security.services;


import soft_afric.clim.shop.clim_shop.security.data.entities.AppRole;
import soft_afric.clim.shop.clim_shop.security.data.entities.AppUser;

public interface SecurityService {

    AppUser getUserByUsername(String username);

    AppUser addUser(String username,String password);

    AppRole addRole(String roleName);

    void addRoleToUser(String username,String roleName);
    void removeRoleToUser(String username,String roleName);
}
