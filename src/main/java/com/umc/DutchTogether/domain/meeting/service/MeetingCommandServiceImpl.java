package com.umc.DutchTogether.domain.meeting.service;

import com.umc.DutchTogether.domain.meeting.converter.MeetingConverter;
import com.umc.DutchTogether.domain.meeting.dto.MeetingRequest;
import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.meeting.repository.MeetingRepository;
import com.umc.DutchTogether.global.apiPayload.code.status.ErrorStatus;
import com.umc.DutchTogether.global.apiPayload.exception.handler.MeetingHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class MeetingCommandServiceImpl implements MeetingCommandService{

    private final MeetingRepository meetingRepository;

    @Override
    public Meeting joinMeeting(MeetingRequest.MeetingDT0 request) {
        String meetingId = request.getMeetingId();
        String password = request.getPassword();
        if( (meetingId == null || meetingId.isEmpty() )&& (password == null || password.isEmpty())) {
            return null;
        }
        Meeting newMeeting = MeetingConverter.toMeetingDTD(request);
        return meetingRepository.save(newMeeting);
    }

}