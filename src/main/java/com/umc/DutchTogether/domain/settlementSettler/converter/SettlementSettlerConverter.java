package com.umc.DutchTogether.domain.settlementSettler.converter;

import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlementSettler.dto.SettlementSettlerResponse;
import com.umc.DutchTogether.domain.settlementSettler.entity.SettlementSettler;
import com.umc.DutchTogether.domain.settlementSettler.entity.Status;
import com.umc.DutchTogether.domain.settler.entity.Settler;

public class SettlementSettlerConverter {
    public static SettlementSettler toSettlementSettlerDTO(Settlement settlement, Settler settler) {
        return SettlementSettler.builder()
                .settlement(settlement)
                .settler(settler)
                .status(Status.COMPLETED)
                .build();
    }
    public static SettlementSettlerResponse.SettlementSettlerResultDTO toSettlementSettlerResultDTO(SettlementSettler settlementSettler) {
        return SettlementSettlerResponse.SettlementSettlerResultDTO.builder()
                .settlerId(settlementSettler.getSettler().getId())
                .settlementTime(settlementSettler.getUpdatedAt())
                .build();
    }
}
