package com.nkb.store.service;

import com.nkb.store.entity.District;

import java.util.List;

public interface DistrictService {
    List<District> getByParent(String parent);

    String getNameByCode(String code);
}
