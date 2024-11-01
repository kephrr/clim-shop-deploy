package soft_afric.clim.shop.clim_shop.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import soft_afric.clim.shop.clim_shop.data.entities.Fournisseur;
import soft_afric.clim.shop.clim_shop.data.repositories.FournisseurRepository;
import soft_afric.clim.shop.clim_shop.services.FournisseurService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FournisseurServiceImpl implements FournisseurService {
    private final FournisseurRepository fournisseurRepository;
    @Override
    public Fournisseur save(Fournisseur data) {
        return fournisseurRepository.save(data);
    }

    @Override
    public Page<Fournisseur> findAll(Pageable pageable) {
        return fournisseurRepository.findAll(pageable);
    }

    @Override
    public List<Fournisseur> findAll() {
        return fournisseurRepository.findAll();
    }

    @Override
    public Optional<Fournisseur> show(Long dataID) {
        return fournisseurRepository.findById(dataID);
    }
}
