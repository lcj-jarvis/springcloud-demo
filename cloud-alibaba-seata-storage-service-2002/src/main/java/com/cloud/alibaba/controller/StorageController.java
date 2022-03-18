package com.cloud.alibaba.controller;

import com.cloud.alibaba.domain.CommonResult;
import com.cloud.alibaba.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 简单de快乐
 * @date 2022-01-06 22:04
 */
@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    /**
     * 扣减库存
     */
    // @RequestMapping("/storage/decrease")
    @PostMapping("/storage/decrease")
    public CommonResult decrease(@RequestParam Long productId, @RequestParam Integer count) {
        storageService.decrease(productId, count);
        return new CommonResult(200,"扣减库存成功！");
    }
}
