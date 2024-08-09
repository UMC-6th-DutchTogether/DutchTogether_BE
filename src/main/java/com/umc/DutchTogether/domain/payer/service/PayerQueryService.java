package com.umc.DutchTogether.domain.payer.service;

import com.umc.DutchTogether.domain.payer.dto.PayerResponse;

public interface PayerQueryService {
    public PayerResponse.PayerNameListDTO getPayerList(Long meetingNum);
    public PayerResponse.PayerInfoListDTO getPayerInfoListDTO(Long settlerId);
}
