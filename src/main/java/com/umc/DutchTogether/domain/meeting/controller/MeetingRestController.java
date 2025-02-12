package com.umc.DutchTogether.domain.meeting.controller;

import com.umc.DutchTogether.domain.meeting.converter.MeetingConverter;
import com.umc.DutchTogether.domain.meeting.dto.MeetingRequest;
import com.umc.DutchTogether.domain.meeting.dto.MeetingResponse;
import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.meeting.service.MeetingCommandService;
import com.umc.DutchTogether.domain.meeting.service.MeetingQueryService;
import com.umc.DutchTogether.global.validation.annotation.ExistMeeting;
import com.umc.DutchTogether.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/meetings")
public class MeetingRestController {

    private final MeetingCommandService meetingCommandService;
    private final MeetingQueryService meetingQueryService;

    @PostMapping("/")
    public ApiResponse<MeetingResponse.MeetingResultDT0> createMeeting(@Valid @RequestBody MeetingRequest.MeetingDT0 request){
        Meeting meeting = meetingCommandService.createMeeting(request);
        return ApiResponse.onSuccess(MeetingConverter.toMeetingResultDTO(meeting));
    }

    @PutMapping("/meetingName")
    public ApiResponse<MeetingResponse.MeetingResultDT0> putMeetingName(@Valid @RequestBody MeetingRequest.PutMeetingNameDT0 request){
        Meeting meeting = meetingCommandService.updateMeetingName(request);
        return ApiResponse.onSuccess(MeetingConverter.toMeetingResultDTO(meeting));
    }

    @GetMapping("/{meetingNum}/link")
    public ApiResponse<MeetingResponse.MeetingLinkResultDT0> getMeetingLink(@ExistMeeting @PathVariable Long meetingNum) {
        MeetingResponse.MeetingLinkResultDT0 result = meetingQueryService.getMeeting(meetingNum);
        return ApiResponse.onSuccess(result);
    }

    @GetMapping("/{link}")
    public ApiResponse<MeetingResponse.SingleSettlementResultDTO> getSingleSettlement(@PathVariable String link) {
        MeetingResponse.SingleSettlementResultDTO meeting = meetingQueryService.getSingleSettlement(link);
        return ApiResponse.onSuccess(meeting);
    }

    @GetMapping("/info/{meetingNum}")
    public ApiResponse<MeetingResponse.MeetingInfoResultDTO> getMeetingInfo(@PathVariable Long meetingNum) {
        MeetingResponse.MeetingInfoResultDTO result = meetingQueryService.getMeetingInfo(meetingNum);
        return ApiResponse.onSuccess(result);
    }


}