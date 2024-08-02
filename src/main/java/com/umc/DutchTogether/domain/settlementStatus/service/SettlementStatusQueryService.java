package com.umc.DutchTogether.domain.settlementStatus.service;

import com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusResponse;

public interface SettlementStatusQueryService {
    public SettlementStatusResponse.SettlementStatusDTO getSettlementStatus(Long settlementId);
}
