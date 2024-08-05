package com.umc.DutchTogether.domain.settlement.controller;

import com.umc.DutchTogether.domain.settlement.dto.*;
import com.umc.DutchTogether.domain.settlement.service.SettlementCommandService;
import com.umc.DutchTogether.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/settlement")
public class SettlementRestController {


    private final SettlementCommandService settlementCommandService;
    //    @PostMapping("/single")
//    @ResponseStatus(HttpStatus.OK)
//    public Object createSingleSettlement(@Validated @RequestBody SingleSettlementCreateRequestDto request, BindingResult bindingResult) {
//
//        // 유효성 검사 실패 시 에러 처리
//        if (bindingResult.hasErrors()) {
//            String errorMessage = ValidationUtils.getFirstErrorMessage(bindingResult);
//            return errorMessage;
//        }
//
//        SingleSettlementCreateResponseDto settlement = settlementService.createSettlement(request);
//
//
//        return settlement;
//    }

    @PostMapping("/single")
    public ApiResponse<SettlementResponse.SettlementDTO> createSingleSettlement(@RequestBody @Valid SettlementRequest.SettlementDTO request) {
        SettlementResponse.SettlementDTO settlement = settlementCommandService.CreateSingleSettlement(request);
        return ApiResponse.onSuccess(settlement);
    }


//    @GetMapping("/single/info")
//    public ApiResponse<SettlementResponse.SettlementDTO> getMeeting(@RequestParam("settlementId") Long settlementId) {
//
//        return ApiResponse.onSuccess(settlement);
//    }

}
