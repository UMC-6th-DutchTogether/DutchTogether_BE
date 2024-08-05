package com.umc.DutchTogether.domain.settlementStatus.converter;

import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.payer.entity.Payer;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusResponse;
import com.umc.DutchTogether.domain.settlementStatus.entity.SettlementStatus;
import com.umc.DutchTogether.domain.settler.entity.Settler;

import java.util.List;

public class SettlementStatusConverter {
    public static SettlementStatusResponse.SettlementStatusDTO toSettlementStatusDTO(Settlement settlement, SettlementStatus settlementStatus , Meeting meeting, Payer payer, List<SettlementStatusResponse.SettlementSettlersDTO> settlersDTOList){
        return SettlementStatusResponse.SettlementStatusDTO.builder()
                .meetingName(meeting.getName())
                .payer(payer.getName())
                .completedNum(settlementStatus.getCompletedPeople())
                .numPeople(settlement.getNumPeople())
                .settlementStatusDTOList(settlersDTOList)
                .build();
    }

    public static SettlementStatusResponse.SettlementSettlersDTO settlementSettlersDTO(Settler settler){
        return SettlementStatusResponse.SettlementSettlersDTO.builder()
                .updateAt(settler.getUpdatedAt())
                .name(settler.getName())
                .build();
    }
}
