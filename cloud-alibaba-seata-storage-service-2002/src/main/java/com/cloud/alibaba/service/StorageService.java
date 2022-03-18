package com.cloud.alibaba.service;

/**
 * @author 简单de快乐
 * @date 2022-01-06 22:02
 */
public interface StorageService {
    /**
     * 扣减库存
     */
    void decrease(Long productId, Integer count);
}
