package com.umc.DutchTogether.domain.settlement.converter;

import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.payer.entity.Payer;
import com.umc.DutchTogether.domain.settlement.dto.SettlementResponse;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;

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
}
