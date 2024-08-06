package com.umc.DutchTogether.domain.settlementSettler.service;

import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlement.repository.SettlementRepository;
import com.umc.DutchTogether.domain.settlementSettler.converter.SettlementSettlerConverter;
import com.umc.DutchTogether.domain.settlementSettler.dto.SettlementSettlerRequest;
import com.umc.DutchTogether.domain.settlementSettler.dto.SettlementSettlerResponse;
import com.umc.DutchTogether.domain.settlementSettler.entity.SettlementSettler;
import com.umc.DutchTogether.domain.settlementSettler.entity.Status;
import com.umc.DutchTogether.domain.settlementSettler.repository.SettlementSettlerRepository;
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
public class SettlementSettlerCommandServiceImpl implements SettlementSettlerCommandService {

    private final SettlementSettlerRepository settlementSettlerRepository;
    private final SettlementRepository settlementRepository;
    private final SettlerRepository settlerRepository;

    @Override
    public SettlementSettlerResponse.SettlementSettlerResultDTO updateStatus(SettlementSettlerRequest.SettlementSettlerDTO request) {
        Settlement settlement = settlementRepository.findById(request.getSettlementId())
                .orElseThrow(() -> new EntityNotFoundException("Settlement not found"));

        Settler settler = settlerRepository.findByName(request.getSettlerName())
                .orElseGet(() -> settlerRepository.save(Settler.builder()
                        .name(request.getSettlerName())
                        .build()));

        SettlementSettler settlementSettler = settlementSettlerRepository.findBySettlementIdAndSettlerName(settlement.getId(), settler.getName())
                .orElseGet(() -> SettlementSettlerConverter.toSettlementSettlerDTO(settlement, settler));

        settlementSettlerRepository.save(settlementSettler);

        return SettlementSettlerConverter.toSettlementSettlerResultDTO(settlementSettler);
    }
}
