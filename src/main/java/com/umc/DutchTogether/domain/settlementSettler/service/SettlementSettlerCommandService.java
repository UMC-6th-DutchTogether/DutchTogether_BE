package com.umc.DutchTogether.domain.settlementSettler.service;

import com.umc.DutchTogether.domain.settlementSettler.dto.SettlementSettlerRequest;
import com.umc.DutchTogether.domain.settlementSettler.dto.SettlementSettlerResponse;

public interface SettlementSettlerCommandService {
    public SettlementSettlerResponse.SettlementSettlerResultDTO updateStatus(SettlementSettlerRequest.SettlementSettlerDTO settlementSettler);
}
