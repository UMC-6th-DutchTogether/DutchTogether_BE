package com.umc.DutchTogether.domain.meeting.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MeetingCreateResponseDto {
    private Long meetingNum;
}
