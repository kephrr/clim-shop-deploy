package soft_afric.clim.shop.clim_shop.security.services.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import soft_afric.clim.shop.clim_shop.security.data.entities.AppRole;
import soft_afric.clim.shop.clim_shop.security.data.entities.AppUser;
import soft_afric.clim.shop.clim_shop.security.data.repositories.AppRoleRepository;
import soft_afric.clim.shop.clim_shop.security.data.repositories.AppUserRepository;
import soft_afric.clim.shop.clim_shop.security.services.SecurityService;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService, UserDetailsService {
    private  final AppUserRepository appUserRepository;
    private  final AppRoleRepository appRoleRepository;
    private  final PasswordEncoder passwordEncoder;
    @Override
    public AppUser getUserByUsername(String username) {
        return appUserRepository.findByLogin(username);
    }

    @Override
    public AppUser addUser(String username, String password) {
        AppUser user = appUserRepository.findByLogin(username);
        if(user != null) throw new RuntimeException("L'utilisateur existe déjà");
        user = new AppUser(username,passwordEncoder.encode(password));
        return appUserRepository.save(user);
    }

    @Override
    public AppRole addRole(String roleName) {
       AppRole role= appRoleRepository.findByRoleName(roleName);
       if(role!=null) throw  new RuntimeException("Ce role existe déjà");
       return appRoleRepository.save(new AppRole(roleName,null));

    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser user = appUserRepository.findByLogin(username);
        if(user==null) throw  new RuntimeException("Cette utilisateur n'existe pas");
        AppRole role   = appRoleRepository.findByRoleName(roleName);
        if(role==null) throw  new RuntimeException("Ce role n'existe pas");
        user.getRoles().add(role);
        appUserRepository.save(user);
    }

    @Override
    public void removeRoleToUser(String username, String roleName) {
        AppUser user = appUserRepository.findByLogin(username);
        if(user==null) throw  new RuntimeException("Cette utilisateur n'existe pas");
        AppRole role   = appRoleRepository.findByRoleName(roleName);
        if(role==null) throw  new RuntimeException("Ce role n'existe pas");
        user.getRoles().removeIf(r-> role ==r );
        appUserRepository.save(user);

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = appUserRepository.findByLogin(username);
        if(user==null) throw new RuntimeException("L'utilisateur n'existe pas");
        return new User(
                user.getLogin(),
                user.getPassword(),
                user.getRoles().stream().map(role-> new SimpleGrantedAuthority(role.getRoleName())).toList());
    }
}
