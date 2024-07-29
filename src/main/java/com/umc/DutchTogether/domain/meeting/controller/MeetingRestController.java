package com.umc.DutchTogether.domain.meeting.controller;

import com.umc.DutchTogether.domain.meeting.converter.MeetingConverter;
import com.umc.DutchTogether.domain.meeting.dto.MeetingRequest;
import com.umc.DutchTogether.domain.meeting.dto.MeetingResponse;
import com.umc.DutchTogether.domain.meeting.service.MeetingCommandService;
import com.umc.DutchTogether.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/meetings")
public class MeetingRestController {

    private final MeetingCommandService meetingCommandService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public ApiResponse<MeetingResponse.MeetingDT0> createMeeting(@Validated @RequestBody MeetingRequest.MeetingDT0 request){
        //serviceImpl에서 그냥 request 넣었을 때 다 검증되도록 변경 예정
        meetingCommandService.CheckValidPassword(request.getPassword());
        return ApiResponse.onSuccess(MeetingConverter.toMeetingDTD(request));
    }
}
