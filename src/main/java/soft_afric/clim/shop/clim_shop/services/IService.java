package soft_afric.clim.shop.clim_shop.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.Optional;

public interface IService<T,ID> {
    T save(T data);
    Page<T> findAll(Pageable pageable);
    List<T> findAll();
    Optional<T> show(ID dataID);
}
