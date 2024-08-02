package com.umc.DutchTogether.domain.settlementStatus.controller;

import com.umc.DutchTogether.domain.settlementStatus.converter.SettlementStatusConverter;
import com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusRequest;
import com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusResponse;
import com.umc.DutchTogether.domain.settlementStatus.service.SettlementStatusCommandService;
import com.umc.DutchTogether.domain.settlementStatus.service.SettlementStatusCommandServiceImpl;
import com.umc.DutchTogether.domain.settlementStatus.service.SettlementStatusQueryServiceImpl;
import com.umc.DutchTogether.global.apiPayload.ApiResponse;
import io.swagger.annotations.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/settlementStatus")
public class SettlementStatusController {

    private final SettlementStatusQueryServiceImpl settlementStatusQueryService;
/*
    @PostMapping("/login")
    public ApiResponse<SettlementStatusResponse.SettlementStatusDTO> settlementStatusLogin(@RequestBody @Valid SettlementStatusRequest.SettlementStatusDTO request) {
        SettlementStatusResponse.SettlementStatusDTO loginResult = settlementStatusCommandService.login(request);
        return ApiResponse.onSuccess(loginResult);
    }
*/

    @GetMapping("/{StatusId}")
    public ApiResponse<SettlementStatusResponse.SettlementStatusDTO> getSettlementStatus(@PathVariable Long StatusId){
        SettlementStatusResponse.SettlementStatusDTO settlementStatus =settlementStatusQueryService.getSettlementStatus(StatusId);
        return ApiResponse.onSuccess(SettlementStatusConverter.toSettlementStatusDTO());
    }

    @PostMapping("/{StatusId}")
    public ApiResponse<SettlementStatusResponse.SettlementStatusDTO> SettlementStatusSearch(@RequestBody @Valid SettlementStatusRequest.SettlementStatusDTO request){
        return null;
    }

}
