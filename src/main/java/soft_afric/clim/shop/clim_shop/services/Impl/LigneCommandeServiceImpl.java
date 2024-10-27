package soft_afric.clim.shop.clim_shop.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import soft_afric.clim.shop.clim_shop.data.entities.LigneCommande;
import soft_afric.clim.shop.clim_shop.data.repositories.LigneCommandeRepository;
import soft_afric.clim.shop.clim_shop.services.LigneCommandeService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LigneCommandeServiceImpl implements LigneCommandeService {
    private final LigneCommandeRepository ligneCommandeRepository;
    @Override
    public LigneCommande save(LigneCommande data) {
        return ligneCommandeRepository.save(data);
    }

    @Override
    public Page<LigneCommande> findAll(Pageable pageable) {
        return ligneCommandeRepository.findAll(pageable);
    }

    @Override
    public List<LigneCommande> findAll() {
        return ligneCommandeRepository.findAll();
    }

    @Override
    public Optional<LigneCommande> show(Long dataID) {
        return ligneCommandeRepository.findById(dataID);
    }
}
