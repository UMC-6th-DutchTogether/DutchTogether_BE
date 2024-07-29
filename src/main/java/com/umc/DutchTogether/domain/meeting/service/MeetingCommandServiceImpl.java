package com.umc.DutchTogether.domain.meeting.service;

import com.umc.DutchTogether.domain.meeting.converter.MeetingConverter;
import com.umc.DutchTogether.domain.meeting.dto.MeetingRequest;
import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.meeting.repository.MeetingRepository;
import com.umc.DutchTogether.global.apiPayload.code.status.ErrorStatus;
import com.umc.DutchTogether.global.exception.handler.MeetingHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional

public class MeetingCommandServiceImpl implements MeetingCommandService{

    private final MeetingRepository meetingRepository;

    @Override
    public Meeting addMeeting(MeetingRequest.MeetingDT0 request) {
        Meeting newMeeting = MeetingConverter.toMeetingDTD(request);
        CheckValidPassword(request.getMeetingId(), request.getPassword());

        return meetingRepository.save(newMeeting);
    }

    private void  CheckValidPassword(String meetingId, String password) {
        if (meetingId == null || meetingId.isEmpty()) {
            throw new MeetingHandler(ErrorStatus._NOT_VALID_ID);
        }
        if (password == null || password.isEmpty()) {
            throw new MeetingHandler(ErrorStatus._NOT_VALID_PW);
        }
    }
}
