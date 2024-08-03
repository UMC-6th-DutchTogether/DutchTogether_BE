package com.umc.DutchTogether.domain.meeting.service;

import com.umc.DutchTogether.domain.meeting.dto.MeetingResponse;
import com.umc.DutchTogether.domain.meeting.entity.Meeting;

public interface MeetingQueryService {
    public Meeting getMeeting(Long meetingNum);
    public MeetingResponse.SingleSettlementResultDTO getSingleSettlement(String link);
}
