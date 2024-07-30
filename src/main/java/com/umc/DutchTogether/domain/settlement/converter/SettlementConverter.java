package com.umc.DutchTogether.domain.settlement.converter;

import com.umc.DutchTogether.domain.settlement.dto.SettlementRequest;
import com.umc.DutchTogether.domain.settlement.dto.SettlementResponse;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;

public class SettlementConverter {

    public static SettlementResponse.SettlementDTO toSettlementDTO(Settlement settlement) {
        return SettlementResponse.SettlementDTO.builder()
                .settlementId(settlement.getId())
                .build();
    }

}
