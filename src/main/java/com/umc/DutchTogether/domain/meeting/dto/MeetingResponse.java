package com.umc.DutchTogether.domain.meeting.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MeetingResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "모임 생성 응답 DTO")
    public static class MeetingResultDT0 {
        private Long meetingNum;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "모임 링크 조회 응답 DTO")
    public static class MeetingLinkResultDT0 {
        private String meetingLink;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "나만 정산하기 정보 조회 DTO")
    public static class SingleSettlementResultDTO {
        private String meetingName;
        private String payer;
        private Long account_num;
        private String bank;
        private int total_amount;
        private int num_people;
        private String receiptUrl;
        private Long settlementId;
    }
}
