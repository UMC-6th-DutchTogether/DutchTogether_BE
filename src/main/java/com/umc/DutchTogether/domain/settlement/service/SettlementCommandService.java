package com.umc.DutchTogether.domain.settlement.service;

import com.umc.DutchTogether.domain.settlement.dto.SettlementRequest;
import com.umc.DutchTogether.domain.settlement.dto.SettlementResponse;

public interface SettlementCommandService {

    public SettlementResponse.SettlementDTO CreateSingleSettlement(SettlementRequest.SettlementDTO request);

    public Boolean updateSettlement(SettlementRequest.SettlementInfoListDTO request);
}
