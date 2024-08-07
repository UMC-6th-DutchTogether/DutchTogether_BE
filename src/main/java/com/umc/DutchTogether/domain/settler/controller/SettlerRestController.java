package com.umc.DutchTogether.domain.settler.controller;

import com.umc.DutchTogether.domain.settlement.service.SettlementCommandService;
import com.umc.DutchTogether.domain.settler.dto.SettlerRequest;
import com.umc.DutchTogether.domain.settler.service.SettlerCommandService;
import com.umc.DutchTogether.global.apiPayload.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/settler")
public class SettlerRestController {

    private final SettlerCommandService settlerCommandService;

    //응답 양식 수정 예정(?)
    @PostMapping("/")
    public ApiResponse<Boolean> createSettler(@RequestBody SettlerRequest.SettlerRequestDTO request) {
        Boolean result = settlerCommandService.createSettler(request);
        return ApiResponse.onSuccess(result);
    }
}
