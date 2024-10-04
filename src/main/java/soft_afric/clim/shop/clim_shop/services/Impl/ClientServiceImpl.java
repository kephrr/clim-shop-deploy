package soft_afric.clim.shop.clim_shop.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import soft_afric.clim.shop.clim_shop.data.entities.Client;
import soft_afric.clim.shop.clim_shop.data.repositories.ClientRepository;
import soft_afric.clim.shop.clim_shop.services.ClientService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    @Override
    public Client save(Client data) {
        return clientRepository.save(data);
    }

    @Override
    public Page<Client> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> show(Long dataID) {
        return clientRepository.findById(dataID);
    }

    @Override
    public Client findByUsername(String username) {
        return clientRepository.findClientByNomCompletAndIsActivedTrue(username);
    }
}
