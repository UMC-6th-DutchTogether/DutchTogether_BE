package com.umc.DutchTogether.domain.payer.service;

import com.umc.DutchTogether.domain.payer.dto.PayerRequest;
import com.umc.DutchTogether.domain.payer.dto.PayerResponse;
import com.umc.DutchTogether.domain.payer.entity.Payer;

public interface PayerCommandService {
    public PayerResponse.PayerListDTO updatePayer(PayerRequest.PayerListDTO request);
    public Long createSettlement(Payer payer, Long meetingNum);
    public PayerResponse.PayerListDTO createPayer(PayerRequest.PayerNameListDTO request);
}
