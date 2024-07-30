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
    public static class MeetingDT0 {
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
}
