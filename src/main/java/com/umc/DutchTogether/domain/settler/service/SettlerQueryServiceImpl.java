package com.umc.DutchTogether.domain.settler.service;

import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlement.repository.SettlementRepository;
import com.umc.DutchTogether.domain.settlementSettler.entity.SettlementSettler;
import com.umc.DutchTogether.domain.settlementSettler.repository.SettlementSettlerRepository;
import com.umc.DutchTogether.domain.settler.dto.SettlerResponse;
import com.umc.DutchTogether.domain.settler.entity.Settler;
import com.umc.DutchTogether.domain.settler.repository.SettlerRepository;
import com.umc.DutchTogether.global.apiPayload.exception.handler.SettlementHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.umc.DutchTogether.global.apiPayload.code.status.ErrorStatus.SETTLEMENT_NOT_FOUND_BY_MEETING;

@Service
@RequiredArgsConstructor
@Transactional
public class SettlerQueryServiceImpl implements SettlerQueryService {

    private final SettlementRepository settlementRepository;
    private final SettlementSettlerRepository settlementSettlerRepository;
    private final SettlerRepository settlerRepository;

    @Override
    public List<Settlement> getSettlements(Long meetingNum) {
        List<Settlement> settlements = settlementRepository.findAllByMeetingId(meetingNum);
        if (settlements.isEmpty()) {
            throw new SettlementHandler(SETTLEMENT_NOT_FOUND_BY_MEETING);
        }
        return settlements;
    }

    @Override
    public SettlerResponse.settlerResponseDTO createSettlers(Long meetingNum) {
        //n:m 매핑이므로 중복이 있을 수 있음.

        List<Settlement> settlements =getSettlements(meetingNum);

        Set<Long> uniqueSettlerIds = settlements.stream()
                .flatMap(settlement -> settlementSettlerRepository.findAllSettlerIdBySettlementId(settlement.getId()).stream())
                .collect(Collectors.toSet());
        List<SettlerResponse.settlerDTO> settlerList = uniqueSettlerIds.stream()
                .map(settlerId->{
                    Settler settler = settlerRepository.findById(settlerId)
                            .orElseThrow(() -> new RuntimeException("Settler not found"));
                    //settlerException 변경
                    return SettlerResponse.settlerDTO.builder()
                            .settlerId(settlerId)
                            .name(settler.getName())
                            .build();
                })
                .collect(Collectors.toList());

        return SettlerResponse.settlerResponseDTO.builder()
                .settlers(settlerList)
                .build();

    }
}