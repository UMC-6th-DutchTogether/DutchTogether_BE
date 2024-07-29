package com.umc.DutchTogether.domain.meeting.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class MeetingCreateResponseDto {
    private Long meetingNum;
}
