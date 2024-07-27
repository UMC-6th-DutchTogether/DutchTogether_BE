package com.umc.DutchTogether.domain.meeting.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MeetingCreateRequestDto {
    private String meeting_id;
    private String password;
    private String meetingName;
}
