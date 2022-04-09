package com.nkb.store.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nkb.store.entity.Product;

import java.util.List;

public interface ProductMapper extends BaseMapper<Product> {

    List<Product> findHotList();

    List<Product> findNewList();
}
