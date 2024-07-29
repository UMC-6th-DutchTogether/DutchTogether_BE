package com.umc.DutchTogether.domain.settlement.controller;

import com.umc.DutchTogether.domain.meeting.repository.MeetingRepository;
import com.umc.DutchTogether.domain.settlement.dto.SingleSettlementInfoResponseDto;
import com.umc.DutchTogether.domain.settlement.dto.SingleSettlementCreateRequestDto;
import com.umc.DutchTogether.domain.settlement.dto.SingleSettlementCreateResponseDto;
import com.umc.DutchTogether.domain.settlement.service.SettlementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/settlement/single")
public class SingleSettlementController {

    private final SettlementService settlementService;
    private final MeetingRepository meetingRepository;


    public SingleSettlementController(SettlementService settlementService, MeetingRepository meetingRepository) {
        this.settlementService = settlementService;
        this.meetingRepository = meetingRepository;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public SingleSettlementCreateResponseDto createSingleSettlement(@RequestBody SingleSettlementCreateRequestDto request) {
        SingleSettlementCreateResponseDto settlement = settlementService.createSettlement(request);

        log.info("생성된 정산: {}", settlement.toString());

        return settlement;
    }

    @GetMapping("/info")
    public SingleSettlementInfoResponseDto getMeetingInfo(@RequestParam("settlementId") Long settlementId) {

        log.info("정산 정보 조회: {}", settlementId);

        return settlementService.getSingleSettlementInfo(settlementId);
    }
}
