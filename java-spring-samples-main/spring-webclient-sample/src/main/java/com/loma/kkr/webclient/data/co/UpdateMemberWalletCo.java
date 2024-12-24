package com.loma.kkr.webclient.data.co;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@ApiModel("回收余额参数")
public class UpdateMemberWalletCo implements Serializable {

    /**
     * 会员账号
     */
    @Schema(name = "memberId", description = "会员id")
    @NotNull(message = "Enter member id")
    private Long memberId;

    /**
     * 游戏id
     */
    @Schema(name = "gameId", description = "游戏id")
    private Long gameId;

    /**
     * 游戏id
     */
    @Schema(name = "merchantId", description = "商户id")
    private Long merchantId;

    /**
     * 操作金额
     */
    @Schema(name = "balance", description = "金额")
    @NotNull(message = "balance")
    private BigDecimal balance;


}
