package com.umc.DutchTogether.domain.payer.converter;

import com.umc.DutchTogether.domain.payer.dto.PayerRequest;
import com.umc.DutchTogether.domain.payer.dto.PayerResponse;
import com.umc.DutchTogether.domain.payer.entity.Payer;

public class PayerConverter {

    public static Payer toPayer(PayerRequest.PayerDTO payerDTO) {
        return Payer.builder()
                .name(payerDTO.getName())
                .accountNum(payerDTO.getAccount())
                .bank(payerDTO.getBank())
                .build();
    }

    public static PayerResponse.PayerDTO toPayerDTO(Payer payer) {
        return PayerResponse.PayerDTO.builder()
                .payerId(payer.getId())
                .build();
    }
}
