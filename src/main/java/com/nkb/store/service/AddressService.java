package com.nkb.store.service;

import com.nkb.store.entity.Address;

import java.util.List;

public interface AddressService {
    void addNewAddress(Integer uid, String username, Address address);

    List<Address> getByUid(Integer uid);

    void setDefault(Integer aid, Integer uid, String username);

    void delete(Integer aid, Integer uid, String username);

    Address getByAid(Integer aid, Integer uid);
}
