package com.umc.DutchTogether.domain.settlementStatus.converter;

import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.payer.entity.Payer;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlementSettler.entity.SettlementSettler;
import com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusResponse;
import com.umc.DutchTogether.domain.settler.entity.Settler;

import java.util.List;

public class SettlementStatusConverter {

    public static SettlementStatusResponse.SettlementStatusLoginDTO toLoginResultDTO(Long meetingNum, String token) {
        return SettlementStatusResponse.SettlementStatusLoginDTO.builder()
                .meetingNum(meetingNum)
                .token(token)
                .build();
    }

    public static SettlementStatusResponse.SettlementStatusDTO toSettlementStatusDTO(Meeting meeting, List<SettlementStatusResponse.SettlementListDTO> settlementDTOList){
        return SettlementStatusResponse.SettlementStatusDTO.builder()
                .meetingName(meeting.getName())
                .settlementListDTO(settlementDTOList)
                .build();
    }

    public static SettlementStatusResponse.SettlementListDTO toSettlementListDTO(Settlement settlement, List<SettlementStatusResponse.SettlementSettlersDTO> settlersDTOList){
        Payer payer = settlement.getPayer();

        int completedNum = settlersDTOList.size();

        return SettlementStatusResponse.SettlementListDTO.builder()
                .settlementId(settlement.getId())
                .numPeople(settlement.getNumPeople())
                .completedNum(completedNum)
                .payer(payer.getName())
                .completedSettler(settlersDTOList)
                .build();
    }

    public static SettlementStatusResponse.SettlementSettlersDTO toSettlementSettlersDTO(SettlementSettler settlementSettler){
        Settler settler = settlementSettler.getSettler();

        return SettlementStatusResponse.SettlementSettlersDTO.builder()
                .settlementTime(settlementSettler.getUpdatedAt())
                .name(settler.getName())
                .build();
    }
}
