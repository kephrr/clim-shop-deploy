package soft_afric.clim.shop.clim_shop.web.dto.request;

import lombok.*;
import soft_afric.clim.shop.clim_shop.data.entities.Adresse;
import soft_afric.clim.shop.clim_shop.data.entities.Fournisseur;
import soft_afric.clim.shop.clim_shop.data.enums.EtatEncours;
import soft_afric.clim.shop.clim_shop.data.enums.EtatPaiement;
import soft_afric.clim.shop.clim_shop.data.enums.ModePaiement;
import soft_afric.clim.shop.clim_shop.web.dto.response.FournisseurDto;

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
    private String siret;
    private String divers;
    private int modePaiement;
    private int etatPaiement;
    private int etatEncours;
    private String commentaire;
    private String ville;
    private String quartier;
    private String numVilla;

    // FINIR LE DTO PUIS FAIRE LA GESTION DE FORMULAIRE + TESTS

    public Fournisseur toEntity(){
        return Fournisseur.builder()
                .societe(this.societe)
                .reference(this.reference)
                .siret(this.siret)
                .divers(this.divers)
                .etatEncours(EtatEncours.values()[this.etatEncours])
                .etatPaiement(EtatPaiement.values()[this.etatPaiement])
                .modePaiement(ModePaiement.values()[this.modePaiement])
                .adresse(new Adresse(this.ville, this.quartier, this.numVilla))
                .build();
    }
    public static FournisseurCreateDto toDto(Fournisseur f){
        return FournisseurCreateDto.builder()
                .id(f.getId())
                .societe(f.getSociete())
                .reference(f.getReference())
                .siret(f.getSiret())
                .divers(f.getDivers())
                .etatEncours(f.getEtatEncours().getIndex())
                .etatPaiement(f.getEtatPaiement().getIndex())
                .modePaiement(f.getModePaiement().getIndex())
                .ville(f.getAdresse().getVille())
                .quartier(f.getAdresse().getQuartier())
                .numVilla(f.getAdresse().getNumVilla())
                .commentaire((f.getCommentaire()==null)?"":f.getCommentaire().toString())
                .nom1((f.getContacts().isEmpty())?"":f.getContacts().get(0).getNom())
                .nom2((f.getContacts().size()<=1)?"":f.getContacts().get(1).getNom())
                .telephone1((f.getContacts().isEmpty())?"":f.getContacts().get(0).getTelephone())
                .telephone2((f.getContacts().size()<=1)?"":f.getContacts().get(1).getTelephone())
                .email1((f.getContacts().isEmpty())?"":f.getContacts().get(0).getEmail())
                .email2((f.getContacts().size()<=1)?"":f.getContacts().get(1).getEmail())
                .build();
    }
}
