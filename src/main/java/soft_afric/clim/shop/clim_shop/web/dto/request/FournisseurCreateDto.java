package soft_afric.clim.shop.clim_shop.web.dto.request;

import lombok.*;
import soft_afric.clim.shop.clim_shop.data.entities.Fournisseur;
import soft_afric.clim.shop.clim_shop.data.enums.EtatEncours;
import soft_afric.clim.shop.clim_shop.data.enums.EtatPaiement;
import soft_afric.clim.shop.clim_shop.data.enums.ModePaiement;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FournisseurCreateDto {
    private Long id;
    private String societe;
    private String reference;
    // Contact 1
    private String nom1;
    private String telephone1;
    private String email1;
    // Contact 2
    private String nom2;
    private String telephone2;
    private String email2;
    // Details
    List<String> clims;
    private String siret;
    private String divers;
    private ModePaiement modePaiement;
    private EtatPaiement etatPaiement;
    private EtatEncours etatEncours;
    private String commentaire;
    private String adresse;


    private static Fournisseur toEntity(FournisseurCreateDto dto){
        return new Fournisseur();
    }
}
