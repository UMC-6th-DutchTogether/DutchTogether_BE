package com.umc.DutchTogether.domain.settlement.controller;

import com.umc.DutchTogether.domain.meeting.repository.MeetingRepository;
import com.umc.DutchTogether.domain.settlement.dto.SingleSettlementInfoResponseDto;
import com.umc.DutchTogether.domain.settlement.dto.SingleSettlementCreateRequestDto;
import com.umc.DutchTogether.domain.settlement.dto.SingleSettlementCreateResponseDto;
import com.umc.DutchTogether.domain.settlement.service.SettlementService;
import com.umc.DutchTogether.global.validation.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
    public Object createSingleSettlement(@Validated @RequestBody SingleSettlementCreateRequestDto request, BindingResult bindingResult) {

        // 유효성 검사 실패 시 에러 처리
        if (bindingResult.hasErrors()) {
            String errorMessage = ValidationUtils.getFirstErrorMessage(bindingResult);
            log.info("나만 정산하기 모임 생성 검증 오류: {}", errorMessage);
            return errorMessage;
        }

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
