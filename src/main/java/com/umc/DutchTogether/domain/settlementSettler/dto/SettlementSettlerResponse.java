package com.umc.DutchTogether.domain.settlementSettler.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class SettlementSettlerResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "정산 완료하기 DTO")
    public static class SettlementSettlerResultDTO {
        private Long settlerId;
        private LocalDateTime settlementTime;
    }
}
