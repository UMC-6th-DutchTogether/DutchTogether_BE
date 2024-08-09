package com.umc.DutchTogether.domain.settlementStatus.service;

import com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusResponse;

import java.util.Optional;

public interface SettlementStatusQueryService {

    SettlementStatusResponse.SettlementStatusDTO getStatus(Long statusId);
    SettlementStatusResponse.SettlementSettlersDTO findSettler(Long settlementId, String settlerName);
}

