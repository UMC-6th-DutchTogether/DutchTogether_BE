package com.umc.DutchTogether.domain.meeting.controller;

import com.umc.DutchTogether.domain.meeting.converter.MeetingConverter;
import com.umc.DutchTogether.domain.meeting.dto.MeetingRequest;
import com.umc.DutchTogether.domain.meeting.dto.MeetingResponse;
import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.meeting.service.MeetingCommandService;
import com.umc.DutchTogether.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/meetings")
public class MeetingRestController {

    private final MeetingCommandService meetingCommandService;

    @PostMapping("/")
    public ApiResponse<MeetingResponse.MeetingDT0> createMeeting(@RequestBody @Valid MeetingRequest.MeetingDT0 request){
        Meeting meeting = meetingCommandService.CreateMeeting(request);
        return ApiResponse.onSuccess(MeetingConverter.toMeetingResultDTD(meeting));
    }
}
