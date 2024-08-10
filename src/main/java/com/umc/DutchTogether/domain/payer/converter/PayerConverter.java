package com.umc.DutchTogether.domain.payer.converter;

import com.umc.DutchTogether.domain.payer.dto.PayerRequest;
import com.umc.DutchTogether.domain.payer.dto.PayerResponse;
import com.umc.DutchTogether.domain.payer.entity.Payer;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;

import java.util.List;

public class PayerConverter {

    public static Payer toPayer(PayerRequest.PayerNameDTO payerDTO) {
        return Payer.builder()
                .name(payerDTO.getName())
                .build();
    }

    public static PayerResponse.PayerDTO toPayerDTO(Payer payer) {
        return PayerResponse.PayerDTO.builder()
                .payerId(payer.getId())
                .build();
    }

    public static PayerResponse.PayerNameDTO toPayerNameDTO(Settlement settlement) {
        return PayerResponse.PayerNameDTO.builder()
                .name(settlement.getPayer().getName())
                .settlementId(settlement.getId())
                .build();
    }

    public static PayerResponse.PayerNameListDTO toPayerNameListDTO(List<PayerResponse.PayerNameDTO> payerNames){
        return PayerResponse.PayerNameListDTO.builder()
                .names(payerNames)
                .build();
    }

    public static PayerResponse.PayerInfoDTO toPayerInfoDTO(Settlement settlement) {
        Payer payer = settlement.getPayer();

        return PayerResponse.PayerInfoDTO.builder()
                .totalAmount(settlement.getTotalAmount())
                .bank(payer.getBank())
                .accountNum(payer.getAccountNum())
                .name(payer.getName())
                .build();
    }

    public static PayerResponse.PayerInfoListDTO payerInfoListDTO(List<PayerResponse.PayerInfoDTO> payerInfos){
        return PayerResponse.PayerInfoListDTO.builder()
                .PayerInfos(payerInfos)
                .build();
    }

    public static PayerResponse.PayerListDTO payerListDTO(List<PayerResponse.PayerDTO> payerDTOs){
        return PayerResponse.PayerListDTO.builder()
                .payers(payerDTOs)
                .build();
    }
}
