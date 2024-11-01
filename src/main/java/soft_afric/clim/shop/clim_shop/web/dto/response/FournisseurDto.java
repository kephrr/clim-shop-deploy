package soft_afric.clim.shop.clim_shop.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import soft_afric.clim.shop.clim_shop.data.entities.*;
import soft_afric.clim.shop.clim_shop.data.enums.EtatEncours;
import soft_afric.clim.shop.clim_shop.data.enums.EtatPaiement;
import soft_afric.clim.shop.clim_shop.data.enums.ModePaiement;

import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class FournisseurDto {
    private Long id;
    private String societe;
    private String reference;

    // Card
    private String contactPrincipal;
    private String telephone;
    private String email;
    // Details
    List<String> contacts;
    List<String> clims;
    private String siret;
    private String divers;
    private ModePaiement modePaiement;
    private EtatPaiement etatPaiement;
    private EtatEncours etatEncours;
    private String commentaire;
    private String adresse;


    public static FournisseurDto toListDto(Fournisseur f){
        Contact contact = f.getContacts().getFirst();
        return FournisseurDto.builder()
                .id(f.getId())
                .societe(f.getSociete())
                .reference(f.getReference())
                .contactPrincipal(contact.getNom())
                .telephone(contact.getTelephone())
                .email(contact.getEmail())
                .build();
    }

    public static FournisseurDto toDetailsDto(Fournisseur f){
        List<String> clims = f.getClims().stream().map(Clim::toString).toList();
        List<String> contacts = f.getContacts().stream().map(Contact::toString).toList();
        Commentaire commentaire = f.getCommentaire();
        return FournisseurDto.builder()
                .id(f.getId())
                .societe(f.getSociete())
                .siret(f.getSiret())
                .reference(f.getReference())
                .divers(f.getDivers())
                .modePaiement(f.getModePaiement())
                .etatPaiement(f.getEtatPaiement())
                .etatEncours(f.getEtatEncours())
                .commentaire(commentaire==null?"Aucun": commentaire.getContent())
                .adresse(f.getAdresse().toString())
                .contacts(contacts)
                .clims(clims)
                .build();
    }
}
