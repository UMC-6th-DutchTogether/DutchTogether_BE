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
        Settlement settlement = settlementRepository.findById(request.getSettlementId()).orElse(null);;

        Long meetingId = settlement.getMeeting().getId();

        // meetingId와 settlerName으로 Settler를 조회, 없으면 새로 생성
        Settler settler = settlerRepository.findByMeetingIdAndSettlerName(meetingId, request.getSettlerName())
                .orElseGet(() -> settlerRepository.save(Settler.builder()
                        .name(request.getSettlerName())
                        .build()));

        SettlementSettler settlementSettler = settlementSettlerRepository.findBySettlementAndSettler(settlement, settler)
                .orElse(null);

        if (settlementSettler == null) {
            settlementSettler = SettlementSettler.builder()
                    .settlement(settlement)
                    .settler(settler)
                    .status(Status.COMPLETED)  // 생성 시에만 상태를 설정
                    .build();
            settlementSettlerRepository.save(settlementSettler);
        } else {
            settlementSettler.setStatus(Status.COMPLETED);
        }

        return SettlementSettlerConverter.toSettlementSettlerResultDTO(settlementSettler);
    }
}
