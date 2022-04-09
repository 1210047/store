package com.nkb.store.service;

import com.nkb.store.common.CartVO;

import java.util.List;

public interface CartService {
    void addToCart(Integer uid, Integer pid, Integer amount, String username);

    List<CartVO> getVOByUid(Integer uid);

    Integer addNum(Integer cid, Integer uid, String username);

    Integer reduceNum(Integer cid, Integer uid, String username);

    List<CartVO> getVOByCids(Integer uid, Integer[] cids);

    Integer deleteByPid(Integer pid);
}
