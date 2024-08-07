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

    @PostMapping("/single")
    public ApiResponse<SettlementResponse.SettlementDTO> createSingleSettlement(@RequestBody @Valid SettlementRequest.SettlementDTO request) {
        SettlementResponse.SettlementDTO settlement = settlementCommandService.CreateSingleSettlement(request);
        return ApiResponse.onSuccess(settlement);
    }

    @PutMapping("/")
    public ApiResponse<Void> updateSettlement(){
        return ApiResponse.onSuccess(null);
    }
}
