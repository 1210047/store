package com.nkb.store.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nkb.store.entity.District;

import java.util.List;

public interface DistrictMapper extends BaseMapper<District> {


    List<District> findByParent(String parent);

    String findNameByCode(String code);
}
