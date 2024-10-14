package soft_afric.clim.shop.clim_shop.data.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name="commentaire")
public class Commentaire extends AbstractEntity{
    private String title;
    private String content;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "EEEE, dd MMM, yyyy")
    private Date date;

    @OneToMany(mappedBy = "commentaire")
    private List<Action> actions;
    @ManyToOne
    Client client;
}
