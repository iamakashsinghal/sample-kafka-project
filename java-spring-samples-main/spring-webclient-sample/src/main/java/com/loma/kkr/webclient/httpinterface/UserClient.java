package com.loma.kkr.webclient.httpinterface;


import com.loma.kkr.common.model.ResultResp;
import com.loma.kkr.common.model.SysUser;
import com.loma.kkr.webclient.constants.APIConstants;
import com.loma.kkr.webclient.data.co.UpdateMemberWalletCo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.math.BigDecimal;

/**
 * @author akash
 */
@HttpExchange("http://kkr-user-center")
public interface UserClient {

    @GetExchange(value = APIConstants.MEMBER_GET_WALLET_BALANCE)
    ResultResp<BigDecimal> getMemberWalletBalance(@RequestParam("memberId") Long memberId,
                                                  @RequestParam("merchantId") Long merchantId);

    @PostExchange(value = APIConstants.MEMBER_UPDATE_WALLET_BALANCE)
    ResultResp<Boolean> updateMemberWalletBalance(@RequestBody UpdateMemberWalletCo updateMemberWalletCo);
    
    @GetExchange(value = APIConstants.USER_GET_USER_BY_ID)
    ResultResp<SysUser> findUserById(@PathVariable("id") Long id);
    
}
