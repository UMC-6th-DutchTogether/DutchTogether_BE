package com.umc.DutchTogether.domain.settlementStatus.dto;

import com.umc.DutchTogether.domain.meeting.validation.annotation.ValidatePassword;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class SettlementStatusRequest {
    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(title = "정산 현황 로그인 요청 DTO")
    public static class SettlementStatusDTO{

        private String meetingId;
        @ValidatePassword
        private String password;

    }

}
