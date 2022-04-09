package com.nkb.store.controller;

import com.nkb.store.entity.Product;
import com.nkb.store.service.ProductService;
import com.nkb.store.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController extends BaseController {
    @Autowired
    private ProductService productService;

    @RequestMapping("hot_list")
    public Result<List<Product>> getHotList() {
        List<Product> data = productService.findHotList();
        return Result.success(data);
    }

    @GetMapping("{id}/details")
    public Result<Product> getById(@PathVariable("id") Integer id) {
        // 调用业务对象执行获取数据
        Product data = productService.findById(id);
        // 返回成功和数据
        return Result.success(data);
    }

    @RequestMapping("new_list")
    public Result<List<Product>> getNewList(){
        List<Product> data = productService.findNewList();
        return Result.success(data);
    }
}
