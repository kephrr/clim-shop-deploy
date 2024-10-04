package soft_afric.clim.shop.clim_shop.services.Impl;

import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import soft_afric.clim.shop.clim_shop.data.entities.Client;
import soft_afric.clim.shop.clim_shop.data.entities.Clim;
import soft_afric.clim.shop.clim_shop.data.entities.Commande;
import soft_afric.clim.shop.clim_shop.data.entities.LigneCommande;
import soft_afric.clim.shop.clim_shop.data.enums.EtatCommande;
import soft_afric.clim.shop.clim_shop.data.repositories.ClimRepository;
import soft_afric.clim.shop.clim_shop.data.repositories.CommandeRepository;
import soft_afric.clim.shop.clim_shop.services.CommandeService;
import soft_afric.clim.shop.clim_shop.web.dto.request.ClimPanierDto;
import soft_afric.clim.shop.clim_shop.web.dto.request.PanierRequestDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommandeServiceImpl implements CommandeService {
    private final ClimRepository climRepository;
    private final CommandeRepository commandeRepository;
    @Override
    public Commande save(Commande data) {
        return commandeRepository.save(data);
    }

    @Override
    public Page<Commande> findAll(Pageable pageable) {
        return commandeRepository.findAll(pageable);
    }

    @Override
    public List<Commande> findAll() {
        return commandeRepository.findAll();
    }

    @Override
    public Optional<Commande> show(Long dataID) {
        return commandeRepository.findById(dataID);
    }

}
