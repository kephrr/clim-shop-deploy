package soft_afric.clim.shop.clim_shop.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import soft_afric.clim.shop.clim_shop.data.entities.Contact;
import soft_afric.clim.shop.clim_shop.data.repositories.ContactRepository;
import soft_afric.clim.shop.clim_shop.services.ContactService;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;
    @Override
    public Contact save(Contact data) {
        return contactRepository.save(data);
    }

    @Override
    public Page<Contact> findAll(Pageable pageable) {
        return contactRepository.findAll(pageable);
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Optional<Contact> show(Long dataID) {
        return contactRepository.findById(dataID);
    }
}
