package com.loma.kkr.webclient.service;

import com.loma.kkr.common.model.ResultResp;
import com.loma.kkr.common.model.SysUser;
import com.loma.kkr.webclient.exception.ApiResponseException;
import com.loma.kkr.webclient.httpinterface.UserClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author akash
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserSampleService {
    
    private final UserClient userClient;
    
    /**
     * get player wallet balance from user service
     *
     * @param userId
     * @param merchantId
     * @return®
     */
    public Optional<BigDecimal> getWalletBalance(Long userId, Long merchantId) {
        log.info("userId: {}, merchantId: {}", userId, merchantId);
        ResultResp<BigDecimal> walletAmountRes = userClient.getMemberWalletBalance(userId, merchantId);
        if (walletAmountRes.getCode() > 0) {
            throw new ApiResponseException(walletAmountRes.getMessage());
        }
        // with some other business logic here
        return Optional.ofNullable(walletAmountRes.getDatas());
    }
    
    public Optional<SysUser> getUserById(Long userId) {
        log.info("userId: {}", userId);
        ResultResp<SysUser> userByIdResp = userClient.findUserById(userId);
        if (userByIdResp.getCode() > 0) {
            throw new ApiResponseException(userByIdResp.getMessage());
        }
        // with some other business logic here
        return Optional.ofNullable(userByIdResp.getDatas());
    }
    
    
}
