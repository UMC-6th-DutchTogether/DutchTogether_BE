package com.umc.DutchTogether.domain.settlement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SettlementResponse {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "정산하기 생성 응답 DTO")
    public static class SettlementDTO {
        private Long settlementId;
    }

    //바뀐 디자인에서 사용할지 안할지 모르겠음..
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "나만 정산하기 정보 응답 DTO")
    public static class SingleSettlementInfoResponseDto {
        private String meetingName;
        private String payer;
        private Long account_num;
        private String bank;
        private int total_amount;
        private int participants;
    }
}
