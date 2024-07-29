package com.umc.DutchTogether.domain.meeting.converter;

import com.umc.DutchTogether.domain.meeting.dto.MeetingRequest;
import com.umc.DutchTogether.domain.meeting.dto.MeetingResponse;
import com.umc.DutchTogether.domain.meeting.entity.Meeting;

public class MeetingConverter {

    public static MeetingResponse.MeetingDT0 toMeetingDTD(MeetingRequest.MeetingDT0 requestBody) {
        return MeetingResponse.MeetingDT0.builder()
                .meetingId(requestBody.getMeetingId())
                .password(requestBody.getPassword())
                .name(requestBody.getName())
                .build();
    }
}
