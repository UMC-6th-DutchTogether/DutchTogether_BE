package com.umc.DutchTogether.domain.settlement.converter;

import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.payer.entity.Payer;
import com.umc.DutchTogether.domain.receipt.entity.Receipt;
import com.umc.DutchTogether.domain.settlement.dto.SettlementRequest;
import com.umc.DutchTogether.domain.settlement.dto.SettlementResponse;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlementStatus.entity.SettlementStatus;

public class SettlementConverter {

    public static SettlementResponse.SettlementDTO toSettlementDTO(Settlement settlement) {
        return SettlementResponse.SettlementDTO.builder()
                .settlementId(settlement.getId())
                .build();
    }

    public static Settlement toSettlement(Payer payer, Meeting meeting) {
        return Settlement.builder()
                .meeting(meeting)
                .payer(payer)
                .build();
    }

    public static Payer toPayer(SettlementRequest.SettlementDTO request) {
        return Payer.builder()
                .name(request.getPayer())
                .accountNum(request.getAccountNumber())
                .bank(request.getBankName())
                .build();
    }

    public static Settlement toSettlement(SettlementRequest.SettlementDTO request,Meeting meeting ,Payer payer, Receipt receipt) {
        return Settlement.builder()
                .meeting(meeting)
                .payer(payer)
                .totalAmount(request.getTotalAmount())
                .numPeople(request.getNumPeople())
                .receipt(receipt)
                .build();
    }

    public static SettlementStatus toSettlementStatus(Settlement settlement){
        return SettlementStatus.builder()
                .settlement(settlement)
                .build();
    }
}
