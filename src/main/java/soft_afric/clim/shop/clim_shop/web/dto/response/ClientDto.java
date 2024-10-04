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
    private String nomComplet;
    private  String tel;
    private  String adresse;
    private  String quartier;
    private  String ville;
    private  String numVilla;


    public  static ClientDto toDto(Client client){
          return  ClientDto.builder()
                  .id(client.getId())
                  .nomComplet(client.getNomComplet())
                  .tel(client.getTel())
                  .adresse(client.getAdresse().toString())
                  .build();
    }
}
