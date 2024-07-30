package com.umc.DutchTogether.domain.settlement.service;

import com.umc.DutchTogether.domain.settlement.dto.SettlementRequest;
import com.umc.DutchTogether.domain.settlement.dto.SettlementResponse;

public interface SettlementCommandService {

    public SettlementResponse.SettlementDTO CreateSettlement(SettlementRequest.SettlementDTO request);
}
