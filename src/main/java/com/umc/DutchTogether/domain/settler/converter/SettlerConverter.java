package com.umc.DutchTogether.domain.settler.converter;

import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlementSettler.entity.SettlementSettler;
import com.umc.DutchTogether.domain.settler.dto.SettlerRequest;
import com.umc.DutchTogether.domain.settler.entity.Settler;

public class SettlerConverter {

    public static Settler toSettler(SettlerRequest.SettlerSettlementsDTO dto) {
        return Settler.builder()
                .name(dto.getName())
                .build();
    }

    public static SettlementSettler toSettlementSettler(Settler settler,Settlement settlement) {

        return SettlementSettler.builder()
                .settler(settler)
                .settlement(settlement)
                .build();
    }
}
