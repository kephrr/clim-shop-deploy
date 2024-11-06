package soft_afric.clim.shop.clim_shop.services;

import soft_afric.clim.shop.clim_shop.data.entities.Contact;

import java.util.List;

public interface ContactService extends IService<Contact, Long> {
    List<Contact> saveAll(List<Contact> contacts);
}
