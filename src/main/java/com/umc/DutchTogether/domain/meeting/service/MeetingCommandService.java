package com.umc.DutchTogether.domain.meeting.service;

import com.umc.DutchTogether.domain.meeting.dto.MeetingRequest;
import com.umc.DutchTogether.domain.meeting.entity.Meeting;

public interface MeetingCommandService {
    public Meeting createMeeting(MeetingRequest.MeetingDT0 request);
    public Meeting updateMeetingName(MeetingRequest.PutMeetingNameDT0 request);
}
