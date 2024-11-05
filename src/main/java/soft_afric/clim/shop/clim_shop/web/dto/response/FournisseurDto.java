package soft_afric.clim.shop.clim_shop.web.dto.response;

import lombok.*;
import soft_afric.clim.shop.clim_shop.data.entities.*;
import soft_afric.clim.shop.clim_shop.data.enums.EtatEncours;
import soft_afric.clim.shop.clim_shop.data.enums.EtatPaiement;
import soft_afric.clim.shop.clim_shop.data.enums.ModePaiement;

import java.util.List;

@NoArgsConstructor
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
        FournisseurDto dto = new FournisseurDto();
        Contact contact;
        if(!f.getContacts().isEmpty()){
            contact = f.getContacts().getFirst();
        }else{
            contact = Contact.builder()
                    .nom("Aucun")
                    .email("Aucun")
                    .telephone("Aucun")
                    .build();
        }
        dto.setId(f.getId());
        dto.setSociete(f.getSociete());
        dto.setReference(f.getReference());
        dto.setContactPrincipal(contact.getNom());
        dto.setTelephone(contact.getTelephone());
        dto.setEmail(contact.getEmail());

        return dto;
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
