package com.umc.DutchTogether.domain.settlementStatus.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class SettlementStatusResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "정산 현황 응답 DTO")
    public static class SettlementStatusDTO{
        private String meetingName;
        private String payer;
        private int participants;
        private int numPeople;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "정산자 응답 DTO")
    public static class SettlementSettlerResponseDTO {
        private String name;
    }
}
