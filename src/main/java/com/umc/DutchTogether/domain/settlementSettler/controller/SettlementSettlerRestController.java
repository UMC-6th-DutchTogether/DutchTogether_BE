package com.umc.DutchTogether.domain.settlementSettler.controller;

import com.umc.DutchTogether.domain.settlementSettler.dto.SettlementSettlerRequest;
import com.umc.DutchTogether.domain.settlementSettler.dto.SettlementSettlerResponse;
import com.umc.DutchTogether.domain.settlementSettler.service.SettlementSettlerCommandService;
import com.umc.DutchTogether.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/settlementSettler")
public class SettlementSettlerRestController {

    private final SettlementSettlerCommandService settlementSettlerCommandService;

    @PutMapping("/updateStatus")
    public ApiResponse<SettlementSettlerResponse.SettlementSettlerResultDTO> updateStatus(@Valid @RequestBody SettlementSettlerRequest.SettlementSettlerDTO request){
        SettlementSettlerResponse.SettlementSettlerResultDTO settlementSettler = settlementSettlerCommandService.updateStatus(request);
        return ApiResponse.onSuccess(settlementSettler);
    }
}
