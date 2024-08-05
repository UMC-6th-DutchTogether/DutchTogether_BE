package com.umc.DutchTogether.domain.settlementStatus.service;

import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlementSettler.entity.SettlementSettler;
import com.umc.DutchTogether.domain.settlementStatus.converter.SettlementStatusConverter;
import com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusResponse;
import com.umc.DutchTogether.domain.settlementStatus.entity.SettlementStatus;
import com.umc.DutchTogether.domain.settlementStatus.respoistory.SettlementStatusRepository;
import com.umc.DutchTogether.domain.settler.entity.Settler;
import com.umc.DutchTogether.domain.settler.repository.SettlerRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SettlementStatusCommandServiceImpl implements SettlementStatusCommandService{
    //Get제외 모든 요청에 대한 비지니스 처리 로그인

    private final SettlementStatusRepository settlementStatusRepository;
    private final SettlerRepository settlerRepository;
    @Override
    public void addSettler(Long statusId, String settlerName) {
        SettlementStatus settlementStatus = settlementStatusRepository.findById(statusId)
                .orElseThrow(() -> new EntityNotFoundException("Settlement Status not found with ID: " + statusId));

        Settler settler = settlerRepository.findByName(settlerName)
                .orElseThrow(() -> new EntityNotFoundException("Settler not found with name: " + settlerName));

        Settlement settlement = settlementStatus.getSettlement();
        SettlementSettler settlementSettler = new SettlementSettler();
        settlementSettler.setSettlement(settlement);
        settlementSettler.setSettler(settler);

        settlement.getSettlementSettlers().add(settlementSettler);
        settlementStatusRepository.save(settlementStatus);
    }

}
