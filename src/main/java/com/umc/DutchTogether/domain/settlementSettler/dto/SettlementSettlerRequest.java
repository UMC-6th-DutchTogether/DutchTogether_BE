package com.umc.DutchTogether.domain.settlementSettler.dto;

import com.umc.DutchTogether.global.validation.annotation.UniqueSettler;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SettlementSettlerRequest {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "정산 완료하기 DTO")
    public static class SettlementSettlerDTO {
        private Long settlementId;
        private String settlerName;
        private String status;
    }
}
