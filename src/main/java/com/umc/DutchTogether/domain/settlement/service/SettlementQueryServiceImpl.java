package com.umc.DutchTogether.domain.settlement.service;

import com.umc.DutchTogether.domain.settlement.dto.SettlementResponse;

public class SettlementQueryServiceImpl implements SettlementQueryService {
    @Override
    public SettlementResponse.SingleSettlementInfoResponseDto getSingleSettlement(String settlementId) {
        // 필요할지 안필요할지 모르겠어서.. 아직 미구현
        return null;
    }
}
