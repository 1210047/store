package com.nkb.store.controller;

import com.nkb.store.common.Result;
import com.nkb.store.service.CartService;
import com.nkb.store.common.JsonResult;
import com.nkb.store.common.CartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("carts")
public class CartController extends BaseController {
    @Autowired
    private CartService cartService;

    @RequestMapping("add_to_cart")
    public Result<?> addToCart(Integer pid, Integer amount, HttpSession session) {
        // 从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行添加到购物车
        cartService.addToCart(uid, pid, amount, username);
        // 返回成功
        return Result.success();
    }

    @GetMapping({"", "/getCart"})
    public Result<List<CartVO>> getVOByUid(HttpSession session) {
        // 从Session中获取uid
        Integer uid = getUidFromSession(session);
        // 调用业务对象执行查询数据
        List<CartVO> data = cartService.getVOByUid(uid);
        for (CartVO cartVO:data) {

        }
        // 返回成功与数据
        return Result.success(data);
    }

    @RequestMapping("{cid}/num/add")
    public Result<Integer> addNum(@PathVariable("cid") Integer cid, HttpSession session) {
        // 从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行增加数量
        Integer data = cartService.addNum(cid, uid, username);
        // 返回成功
        return Result.success(data);
    }

    @RequestMapping("{cid}/num/reduce")
    public Result<Integer> reduceNum(@PathVariable("cid") Integer cid, HttpSession session) {
        // 从Session中获取uid和username
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        // 调用业务对象执行增加数量
        Integer data = cartService.reduceNum(cid, uid, username);
        // 返回成功
        return Result.success(data);
    }

    @GetMapping("list")
    public Result<List<CartVO>> getVOByCids(Integer[] cids, HttpSession session) {
        // 从Session中获取uid
        Integer uid = getUidFromSession(session);
        // 调用业务对象执行查询数据
        List<CartVO> data = cartService.getVOByCids(uid, cids);
        // 返回成功与数据
        return Result.success(data);
    }

    @RequestMapping("{pid}/delete/deletebypid")
    public Result<Integer> deleteBypid(@PathVariable("pid") Integer pid) {

        Integer data = cartService.deleteByPid(pid);

        // 返回成功
        return Result.success(data);
    }

    @RequestMapping("/delete/deletebypids")
    public Result<Integer> deleteBypids(@RequestBody int[] check_val) {

        for (int pid:check_val) {
            cartService.deleteByPid(pid);
        }
        // 返回成功
        return Result.success();
    }
}
