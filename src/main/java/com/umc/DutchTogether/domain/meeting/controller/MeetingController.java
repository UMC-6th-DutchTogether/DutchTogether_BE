package com.umc.DutchTogether.domain.meeting.controller;

import com.umc.DutchTogether.domain.meeting.dto.MeetingCreateRequestDto;
import com.umc.DutchTogether.domain.meeting.dto.MeetingCreateResponseDto;
import com.umc.DutchTogether.domain.meeting.service.MeetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/meetings")
public class MeetingController {
    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    // 검증 애노테이션 추가 해야함
    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public MeetingCreateResponseDto createMeeting(@RequestBody MeetingCreateRequestDto request) {
        MeetingCreateResponseDto meeting = meetingService.createMeeting(request);

        return meeting;
    }
}
