package com.umc.DutchTogether.domain.payer.service;

import com.umc.DutchTogether.domain.payer.dto.PayerRequest;
import com.umc.DutchTogether.domain.payer.dto.PayerResponse;

public interface PayerCommandService {
    public PayerResponse.PayerListDTO createPayer(PayerRequest.PayerListDTO request);
}
