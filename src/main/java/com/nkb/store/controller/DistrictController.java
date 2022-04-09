package com.nkb.store.controller;

import com.nkb.store.common.Result;
import com.nkb.store.entity.District;
import com.nkb.store.service.DistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("districts")
public class DistrictController extends BaseController {

    @Autowired
    private DistrictService districtService;

    @GetMapping({"", "/"})
    public Result<List<District>> getByParent(String parent) {
        List<District> data = districtService.getByParent(parent);
        return Result.success(data);
    }
}
