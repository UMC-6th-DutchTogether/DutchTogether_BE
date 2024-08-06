package com.umc.DutchTogether.domain.payer.controller;

import com.umc.DutchTogether.domain.payer.dto.PayerRequest;
import com.umc.DutchTogether.domain.payer.dto.PayerResponse;
import com.umc.DutchTogether.domain.payer.service.PayerCommandService;
import com.umc.DutchTogether.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/payers")
public class PayerRestController {

    private final PayerCommandService payerCommandService;

    @PostMapping("/")
    public ApiResponse<PayerResponse.PayerListDTO> createPayer(@Valid @RequestBody PayerRequest.PayerListDTO request) {
        PayerResponse.PayerListDTO payer = payerCommandService.createPayer(request);
        return ApiResponse.onSuccess(payer);
    }
}
