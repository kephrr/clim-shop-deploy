package soft_afric.clim.shop.clim_shop.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import soft_afric.clim.shop.clim_shop.data.entities.Categorie;
import soft_afric.clim.shop.clim_shop.data.repositories.CategorieRepository;
import soft_afric.clim.shop.clim_shop.services.CategorieService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategorieServiceImpl implements CategorieService {
    private final CategorieRepository categorieRepository;
    @Override
    public Categorie save(Categorie data) {
        return categorieRepository.save(data);
    }

    @Override
    public Page<Categorie> findAll(Pageable pageable) {
        return categorieRepository.findAll(pageable);
    }

    @Override
    public List<Categorie> findAll() {
        return categorieRepository.findAll();
    }

    @Override
    public Optional<Categorie> show(Long dataID) {
        return categorieRepository.findById(dataID);
    }
}
