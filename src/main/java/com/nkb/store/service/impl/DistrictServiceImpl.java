package com.nkb.store.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nkb.store.entity.District;
import com.nkb.store.mapper.DistrictMapper;
import com.nkb.store.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    @Autowired
    private DistrictMapper districtMapper;

    @Override
    public List<District> getByParent(String parent) {
//        QueryWrapper<District> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("parent",parent);
//        List<District> list = districtMapper.selectList(queryWrapper);
        List<District> list = districtMapper.findByParent(parent);
        /**
         * 在进行网络传输数据时，为了尽量避免无效数据的传递，可以将无效数据设置为null，
         * 可以节省流量，另一方面提升了效率
         *
         */
        for (District district : list) {
            district.setId(null);
            district.setParent(null);
        }
        return list;
    }

    @Override
    public String getNameByCode(String code) {
        return districtMapper.findNameByCode(code);
    }
}
