package com.umc.DutchTogether.domain.settler.service;

import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settler.dto.SettlerResponse;
import com.umc.DutchTogether.domain.settler.entity.Settler;

import java.util.List;

public interface SettlerQueryService {
    //for get method

    public List<Settlement> getSettlements(Long meetingNum);
    public SettlerResponse.settlerResponseDTO createSettlers(Long meetingNum);
}
