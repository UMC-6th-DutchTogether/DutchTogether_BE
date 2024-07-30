package com.umc.DutchTogether.domain.settlement.service;

import com.umc.DutchTogether.domain.settlement.dto.SettlementResponse;

public interface SettlementQueryService {

    public SettlementResponse.SingleSettlementInfoResponseDto getSingleSettlement(String settlementId);
}
