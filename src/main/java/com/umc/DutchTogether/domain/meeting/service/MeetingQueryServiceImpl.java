package com.umc.DutchTogether.domain.meeting.service;

import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.meeting.repository.MeetingRepository;
import com.umc.DutchTogether.global.apiPayload.code.status.ErrorStatus;
import com.umc.DutchTogether.global.apiPayload.exception.handler.MeetingHandler;
import com.umc.DutchTogether.global.apiPayload.exception.handler.SettlementHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MeetingQueryServiceImpl implements MeetingQueryService{

    private final MeetingRepository meetingRepository;

    @Override
    public Meeting getMeeting(Long meetingNum) {
        return meetingRepository.findById(meetingNum).orElse(null);
    }
}
