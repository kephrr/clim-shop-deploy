package soft_afric.clim.shop.clim_shop.security.data.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import soft_afric.clim.shop.clim_shop.data.entities.AbstractEntity;

import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="roles")
public class AppRole  extends AbstractEntity {
    @Column(nullable = false,unique = true)
    private  String roleName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AppRole appRole = (AppRole) o;
        return Objects.equals(roleName, appRole.roleName);
    }

    public AppRole(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), roleName);
    }

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "roles")

    private List<AppUser> users ;
}
