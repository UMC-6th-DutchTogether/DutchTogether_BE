package com.umc.DutchTogether.domain.meeting.service;

import com.umc.DutchTogether.domain.meeting.entity.Meeting;

import java.util.Optional;

public interface MeetingQueryService {
    public Meeting getMeeting(Long meetingNum);

}
