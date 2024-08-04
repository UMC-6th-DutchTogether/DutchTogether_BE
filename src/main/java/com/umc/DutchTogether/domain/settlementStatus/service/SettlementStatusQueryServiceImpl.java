package com.umc.DutchTogether.domain.settlementStatus.service;

import com.umc.DutchTogether.domain.settlementSettler.entity.SettlementSettler;
import com.umc.DutchTogether.domain.settlementStatus.converter.SettlementStatusConverter;
import com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusResponse;
import com.umc.DutchTogether.domain.settlementStatus.entity.SettlementStatus;
import com.umc.DutchTogether.domain.settlementStatus.respoistory.SettlementStatusRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class SettlementStatusQueryServiceImpl implements SettlementStatusQueryService{
    //Get요청에 대한 비지니스 로직 처리

    private final SettlementStatusRepository settlementStatusRepository;
        @Override
        public SettlementStatusResponse.SettlementStatusDTO getStatus(Long statusId) {
            SettlementStatus settlementStatus = settlementStatusRepository.findById(statusId)
                    .orElseThrow(() -> new EntityNotFoundException("Settlement Status not found with ID: " + statusId));
            return SettlementStatusConverter.toSettlementStatusDTO(settlementStatus);
        }

        @Override
        public SettlementStatusResponse.SettlementSettlerResponse getSettler(Long statusId, String settlerName) {
            SettlementStatus settlementStatus = settlementStatusRepository.findById(statusId)
                    .orElseThrow(() -> new EntityNotFoundException("Settlement Status not found with ID: " + statusId));

            for (SettlementSettler settler : settlementStatus.getSettlement().getSettlementSettlers()) {
                if (settler.getSettler().getName().equalsIgnoreCase(settlerName)) {
                    return SettlementStatusResponse.SettlementSettlerResponse.builder()
                            .name(settler.getSettler().getName())
                            .build();
                }
            }
            throw new EntityNotFoundException("Settler not found with name: " + settlerName);
        }
    }

