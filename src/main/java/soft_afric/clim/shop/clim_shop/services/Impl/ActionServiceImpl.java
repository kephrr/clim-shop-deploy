package soft_afric.clim.shop.clim_shop.services.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import soft_afric.clim.shop.clim_shop.data.entities.Action;
import soft_afric.clim.shop.clim_shop.data.enums.ActionType;
import soft_afric.clim.shop.clim_shop.data.repositories.ActionRepository;
import soft_afric.clim.shop.clim_shop.services.ActionService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ActionServiceImpl implements ActionService {
    private final ActionRepository actionRepository;

    @Override
    public Action save(Action data) {
        return actionRepository.save(data);
    }

    @Override
    public Page<Action> findAll(Pageable pageable) {
        return actionRepository.findAll(pageable);
    }

    @Override
    public List<Action> findAll() {
        return actionRepository.findAll();
    }

    @Override
    public Optional<Action> show(Long dataID) {
        return actionRepository.findById(dataID);
    }

    @Override
    public List<Action> findByCommentAndType(Long id, ActionType type) {
        List<Action> actions = actionRepository.findAllByIsActivedTrueAndCommentaire_Id(id);
        List<Action> data = new ArrayList<>();
        for (Action action : actions) {
            if (action.getType().equals(type)) {
                data.add(action);
            }
        }
        return data;
    }
}
