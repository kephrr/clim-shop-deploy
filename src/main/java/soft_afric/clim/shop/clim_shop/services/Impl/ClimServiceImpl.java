package soft_afric.clim.shop.clim_shop.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import soft_afric.clim.shop.clim_shop.data.entities.Categorie;
import soft_afric.clim.shop.clim_shop.data.entities.Clim;
import soft_afric.clim.shop.clim_shop.data.entities.Marque;
import soft_afric.clim.shop.clim_shop.data.repositories.ClimRepository;
import soft_afric.clim.shop.clim_shop.services.ClimService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClimServiceImpl implements ClimService {
    private final ClimRepository climRepository;
    @Override
    public Clim save(Clim data) {
        return climRepository.save(data);
    }

    @Override
    public Page<Clim> findAll(Pageable pageable) {
        return climRepository.findAll(pageable);
    }

    @Override
    public List<Clim> findAll() {
        return climRepository.findAllByIsActivedTrue();
    }

    @Override
    public Optional<Clim> show(Long dataID) {
        return climRepository.findById(dataID);
    }

    @Override
    public List<Clim> findAllPromotedClims() {
        return climRepository.findAllByIsActivedTrueAndPromotionGreaterThan(0);
    }

    @Override
    public List<Clim> findAllByCategorieAndMarqueAndBudget(Categorie categorie, Marque marque, int budget) {
        return climRepository.findAllByMarqueCategorieAndBudget(categorie, marque, budget);
    }

    @Override
    public List<Clim> findAllBySearchedKEyword(String keyword) {
        return climRepository.findAllBySearchedKEyword(keyword);
    }
}
