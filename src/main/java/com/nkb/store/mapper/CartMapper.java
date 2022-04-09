package com.nkb.store.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nkb.store.common.CartVO;
import com.nkb.store.entity.Cart;

import java.util.List;

public interface CartMapper extends BaseMapper<Cart> {
    List<CartVO> findVOByUid(Integer uid);

    List<CartVO> findVOByCids(Integer[] cids);
}
