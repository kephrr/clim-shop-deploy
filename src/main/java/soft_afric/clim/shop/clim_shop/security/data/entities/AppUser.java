package soft_afric.clim.shop.clim_shop.security.data.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import soft_afric.clim.shop.clim_shop.data.entities.AbstractEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="type")
@DiscriminatorValue(value="Admin")
public class AppUser extends AbstractEntity {
    @Column(nullable = false,unique = true)
    private  String login;
    @Column(nullable = false)
    private  String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns= @JoinColumn(name = "role_id")
    )
    private List<AppRole> roles = new ArrayList<AppRole>();

    public AppUser(String login, String password) {
        this.login = login;
        this.password = password;
    }
}


