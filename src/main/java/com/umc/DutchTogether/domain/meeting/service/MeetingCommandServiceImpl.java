package com.umc.DutchTogether.domain.meeting.service;

import com.umc.DutchTogether.domain.meeting.converter.MeetingConverter;
import com.umc.DutchTogether.domain.meeting.dto.MeetingRequest;
import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.meeting.repository.MeetingRepository;
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
        // id,pw가 있을 때만 저장
        Meeting newMeeting = MeetingConverter.toMeetingDTD(request);
        return meetingRepository.save(newMeeting);
    }
}
