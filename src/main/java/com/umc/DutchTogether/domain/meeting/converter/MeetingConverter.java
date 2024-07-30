package com.umc.DutchTogether.domain.meeting.converter;

import com.umc.DutchTogether.domain.meeting.dto.MeetingRequest;
import com.umc.DutchTogether.domain.meeting.dto.MeetingResponse;
import com.umc.DutchTogether.domain.meeting.entity.Meeting;

public class MeetingConverter {

    public static Meeting toMeetingDTD(MeetingRequest.MeetingDT0 requestBody) {
        return Meeting.builder()
                .meetingId(requestBody.getMeetingId())
                .password(requestBody.getPassword())
                .name(requestBody.getName())
                .build();
    }

    public static MeetingResponse.MeetingDT0 toMeetingResultDTD(Meeting meeting) {
        if (meeting == null) {
            return null;
        }
        return MeetingResponse.MeetingDT0.builder()
                .meetingNum(meeting.getId())
                .build();
    }
}
