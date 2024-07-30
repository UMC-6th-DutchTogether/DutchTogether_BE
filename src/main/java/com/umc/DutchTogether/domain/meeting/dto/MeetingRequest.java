package com.umc.DutchTogether.domain.meeting.dto;

import com.umc.DutchTogether.domain.meeting.validation.annotation.ExistMeeting;
import com.umc.DutchTogether.domain.meeting.validation.annotation.ValidatePassword;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MeetingRequest {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @ExistMeeting
    public static class MeetingDT0{
        private String meetingId;
        @ValidatePassword
        private String password;
        @NotNull(message = "모임 이름을 입력해주세요")
        private String name;
    }
}
