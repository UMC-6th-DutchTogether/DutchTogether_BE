package com.umc.DutchTogether.domain.settlementStatus.service;

import com.umc.DutchTogether.domain.meeting.repository.MeetingRepository;
import com.umc.DutchTogether.domain.settlementStatus.converter.SettlementStatusConverter;
import com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusResponse;
import com.umc.DutchTogether.domain.settlementStatus.entity.SettlementStatus;
import com.umc.DutchTogether.domain.settlementStatus.respoistory.SettlementStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusResponse.*;

@Service
@RequiredArgsConstructor
@Transactional
public class SettlementStatusQueryServiceImpl implements SettlementStatusQueryService{
    //Get요청에 대한 비지니스 로직 처리

    private final SettlementStatusRepository settlementStatusRepository;


    public SettlementStatusDTO getSettlementStatus(Long settlementId) {
        SettlementStatus settlementStatus = settlementStatusRepository.findById(settlementId)
                .orElseThrow(() -> new RuntimeException("SettlementStatus not found with id " + settlementId));
        return SettlementStatusConverter.toSettlementStatusDTO(settlementStatus);
    }

}
