package soft_afric.clim.shop.clim_shop.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import soft_afric.clim.shop.clim_shop.data.entities.Commentaire;
import soft_afric.clim.shop.clim_shop.data.enums.ActionType;
import soft_afric.clim.shop.clim_shop.data.repositories.CommentaireRepository;
import soft_afric.clim.shop.clim_shop.services.CommentaireService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentaireServiceImpl implements CommentaireService {
    private final CommentaireRepository commentaireRepository;
    @Override
    public Commentaire save(Commentaire data) {
        return commentaireRepository.save(data);
    }

    @Override
    public Page<Commentaire> findAll(Pageable pageable) {
        return commentaireRepository.findAll(pageable);
    }

    @Override
    public List<Commentaire> findAll() {
        return commentaireRepository.findAll();
    }

    @Override
    public Optional<Commentaire> show(Long dataID) {
        return commentaireRepository.findById(dataID);
    }

    @Override
    public List<Commentaire> findFourthBetterComments() {
        return commentaireRepository.findFourthFirstComments();
    }
}
