package com.cloud.alibaba.service;

import java.math.BigDecimal;

/**
 * @author 简单de快乐
 * @date 2022-01-06 22:26
 */
public interface AccountService {

    /**
     * 扣减账户余额
     * @param userId 用户id
     * @param money 金额
     */
    void decrease(Long userId, BigDecimal money);
}
