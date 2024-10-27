package soft_afric.clim.shop.clim_shop.web.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import soft_afric.clim.shop.clim_shop.data.entities.Adresse;
import soft_afric.clim.shop.clim_shop.data.entities.Client;
import soft_afric.clim.shop.clim_shop.web.dto.response.ClientDto;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientRequestDto {
    private  Long id;
    private  String nomComplet;
    private  String password;
    private  String tel;
    private  String quartier;
    private  String ville;
    private  String villa;
    private String comment;
    private int numero;

    public  static ClientRequestDto toDto(Client client){
        return  ClientRequestDto.builder()
                .id(client.getId())
                .numero(client.getNumero())
                .nomComplet(client.getNomComplet())
                .password(client.getPassword())
                .tel(client.getTel())
                .ville(client.getAdresse().getVille())
                .quartier(client.getAdresse().getQuartier())
                .villa(client.getAdresse().getNumVilla())
                .comment(client.getCommentaire())
                .build();
    }

    public   Client toEntity(){
        Client cl=  Client.builder()
                .nomComplet(this.getNomComplet())
                .adresse(new Adresse(this.quartier,this.ville,this.villa))
                .tel(this.tel)
                .commandes(null)
                .build();
        cl.setId(this.id);
        return  cl;
    }
}

