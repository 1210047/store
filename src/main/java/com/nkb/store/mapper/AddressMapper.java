package com.nkb.store.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nkb.store.entity.Address;

import java.util.List;

public interface AddressMapper extends BaseMapper<Address> {
    Address findLastModified(Integer uid);

    int updateNoneDefaultByUid(Integer uid);

    List<Address> findByUid(Integer uid);

    Address findByAid(Integer aid);
}
