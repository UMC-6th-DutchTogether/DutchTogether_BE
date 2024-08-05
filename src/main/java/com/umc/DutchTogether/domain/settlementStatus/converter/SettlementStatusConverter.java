package com.umc.DutchTogether.domain.settlementStatus.converter;

import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.payer.entity.Payer;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusResponse;
import com.umc.DutchTogether.domain.settlementStatus.entity.SettlementStatus;

public class SettlementStatusConverter {
    public static SettlementStatusResponse.SettlementStatusDTO toSettlementStatusDTO(Settlement settlement,SettlementStatus settlementStatus ,Meeting meeting, Payer payer){
        return SettlementStatusResponse.SettlementStatusDTO.builder()
                .meetingName(meeting.getName())
                .payer(payer.getName())
                .participants(settlementStatus.getCompletedPeople())
                .numPeople(settlement.getNumPeople())
                .build();
    }
}
