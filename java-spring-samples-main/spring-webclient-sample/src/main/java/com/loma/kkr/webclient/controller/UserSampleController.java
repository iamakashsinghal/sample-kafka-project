package com.loma.kkr.webclient.controller;

import com.loma.kkr.common.enums.ApiResponseEnum;
import com.loma.kkr.common.model.ResultResp;
import com.loma.kkr.common.model.SysUser;
import com.loma.kkr.webclient.service.UserSampleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * @author akash
 */
@RestController
@RequiredArgsConstructor
public class UserSampleController {
    
    private final UserSampleService userSampleService;
    
    @GetMapping("/getWalletBalance")
    public ResultResp<BigDecimal> triggerGetWalletBalance(@RequestParam("userId") Long userId,
                                                      @RequestParam("merchantId") Long merchantId) {
        Optional<BigDecimal> optBalance = userSampleService.getWalletBalance(userId, merchantId);
        return optBalance.map(bigDecimal ->
                ResultResp.succeed(bigDecimal, ApiResponseEnum.SUCCESS.getTitle()))
                .orElseGet(() -> ResultResp.failed(ApiResponseEnum.BADREQEUST.getTitle()));
    }
    
    @GetMapping("/users/{userId}")
    public ResultResp<SysUser> getUserById(@PathVariable("userId") Long userId) {
        Optional<SysUser> optBalance = userSampleService.getUserById(userId);
        return optBalance.map(user ->
                ResultResp.succeed(user, ApiResponseEnum.SUCCESS.getTitle()))
                .orElseGet(() -> ResultResp.failed(ApiResponseEnum.BADREQEUST.getTitle()));
    }
    
}
