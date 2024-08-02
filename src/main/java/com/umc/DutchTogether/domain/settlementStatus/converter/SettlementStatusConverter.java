package com.umc.DutchTogether.domain.settlementStatus.converter;

import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.payer.entity.Payer;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusResponse;
import com.umc.DutchTogether.domain.settlementStatus.entity.SettlementStatus;

public class SettlementStatusConverter {
    public static SettlementStatusResponse.SettlementStatusDTO toSettlementStatusDTO(SettlementStatus settlementStatus){

        Settlement settlement = settlementStatus.getSettlement();
        Meeting meeting = settlement.getMeeting();
        Payer payer = settlement.getPayer();

        return SettlementStatusResponse.SettlementStatusDTO.builder()
                .meetingId(meeting.getId())
                .meetingName(meeting.getName())
                .payer(payer.getName())
                .participants(settlement.getParticipants())
                .numPeople(settlement.getNumPeople())
                .build();
    }



}
