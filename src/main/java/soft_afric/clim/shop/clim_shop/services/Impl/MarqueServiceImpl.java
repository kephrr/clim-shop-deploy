package soft_afric.clim.shop.clim_shop.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import soft_afric.clim.shop.clim_shop.data.entities.Marque;
import soft_afric.clim.shop.clim_shop.data.repositories.MarqueRepository;
import soft_afric.clim.shop.clim_shop.services.MarqueService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MarqueServiceImpl implements MarqueService {
    private final MarqueRepository marqueRepository;
    @Override
    public Marque save(Marque data) {
        return marqueRepository.save(data);
    }

    @Override
    public Page<Marque> findAll(Pageable pageable) {
        return marqueRepository.findAll(pageable);
    }

    @Override
    public List<Marque> findAll() {
        return marqueRepository.findAll();
    }

    @Override
    public Optional<Marque> show(Long dataID) {
        return marqueRepository.findById(dataID);
    }
}
