package soft_afric.clim.shop.clim_shop.web.dto.response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import soft_afric.clim.shop.clim_shop.data.entities.Client;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientDto {
    private  Long id;
    private  int numero;
    private String nomComplet;
    private  String tel;
    private  String adresse;
    private  String quartier;
    private  String ville;
    private  String numVilla;
    private String comment;

    public  static ClientDto toDto(Client client){
          return  ClientDto.builder()
                  .id(client.getId())
                  .numero(client.getNumero())
                  .nomComplet(client.getNomComplet())
                  .tel(client.getTel())
                  .adresse(client.getAdresse().toString())
                  .comment(client.getCommentaire())
                  .build();
    }
}
