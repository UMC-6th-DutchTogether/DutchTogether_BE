package com.umc.DutchTogether.domain.settlementStatus.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class SettlementStatusRequest {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "정산 현황 로그인 요청 DTO")
    public static class SettlementStatusDTO{
        private String meetingId;
        private String password;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "입금자명 추가 요청 DTO")
    public static class settlerInfo {
        @NotNull
        private String name;
        @NotNull
        private Long settlementId;
    }
    
}
