package com.umc.DutchTogether.domain.meeting.service;

import com.umc.DutchTogether.domain.meeting.dto.MeetingCreateRequestDto;
import com.umc.DutchTogether.domain.meeting.dto.MeetingCreateResponseDto;
import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.meeting.repository.MeetingRepository;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlement.service.SettlementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final SettlementService settlementService;

    public MeetingService(MeetingRepository meetingRepository, SettlementService settlementService) {
        this.meetingRepository = meetingRepository;
        this.settlementService = settlementService;
    }

    // Meeting 데이터 저장
    @Transactional
    public MeetingCreateResponseDto createMeeting(MeetingCreateRequestDto request) {
        Meeting.builder().name(request.getMeetingName());
        if(request.getMeeting_id() != null){
            Meeting.builder().meetingId(request.getMeeting_id());
            Meeting.builder().password(request.getPassword());
        }
        Meeting meeting = meetingRepository.save(Meeting.builder().build());

        // Meeting 저장
        Meeting savedMeeting = meetingRepository.save(meeting);

        // MeetingCreateResponseDto 생성 및 반환
        return MeetingCreateResponseDto.builder()
                .meeting_num(savedMeeting.getId())
                .build();
    }
}
