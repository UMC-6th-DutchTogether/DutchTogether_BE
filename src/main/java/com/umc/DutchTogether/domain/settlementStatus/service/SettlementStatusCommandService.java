package com.umc.DutchTogether.domain.settlementStatus.service;

import com.umc.DutchTogether.domain.meeting.dto.MeetingRequest;
import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusRequest;
import com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusResponse;
import com.umc.DutchTogether.domain.settlementStatus.entity.SettlementStatus;

import java.util.Optional;

public interface SettlementStatusCommandService {
    public SettlementStatusResponse.SettlementStatusLoginDTO login(SettlementStatusRequest.SettlementStatusDTO request);
    public Boolean addSettler(SettlementStatusRequest.settlerInfo settlerInfo);
}
