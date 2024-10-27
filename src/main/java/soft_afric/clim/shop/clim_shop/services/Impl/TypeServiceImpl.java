package soft_afric.clim.shop.clim_shop.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import soft_afric.clim.shop.clim_shop.data.entities.Type;
import soft_afric.clim.shop.clim_shop.data.repositories.TypeRepository;
import soft_afric.clim.shop.clim_shop.services.TypeService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TypeServiceImpl implements TypeService {
    private final TypeRepository typeRepository;
    @Override
    public Optional<Type> findByLiblle(String libelle) {
        return typeRepository.findByLibelleAndIsActivedTrue(libelle);
    }

    @Override
    public Type save(Type data) {
        return typeRepository.save(data);
    }

    @Override
    public Page<Type> findAll(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Override
    public List<Type> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public Optional<Type> show(Long dataID) {
        return typeRepository.findById(dataID);
    }
}
