package com.umc.DutchTogether.domain.settlementStatus.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class SettlementStatusResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "정산 현황 로그인 응답 DTO")
    public static class SettlementStatusLoginDTO{
        private Long meetingNum;
        private String token;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "정산 현황 응답 DTO")
    public static class SettlementStatusDTO{
        private String meetingName;
        private List<SettlementListDTO> settlementListDTO;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "정산 DTO")
    public static class SettlementListDTO {
        private Long settlementId;
        private int numPeople;
        private int completedNum;
        private String payer;
        private List<SettlementSettlersDTO> completedSettler;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "정산자 DTO")
    public static class SettlementSettlersDTO {
        private String name;
        private LocalDateTime settlementTime;
    }
}
