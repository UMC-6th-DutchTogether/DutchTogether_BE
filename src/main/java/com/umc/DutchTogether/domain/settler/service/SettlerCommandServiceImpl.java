package com.umc.DutchTogether.domain.settler.service;

import com.umc.DutchTogether.domain.settlement.repository.SettlementRepository;
import com.umc.DutchTogether.domain.settlementSettler.entity.SettlementSettler;
import com.umc.DutchTogether.domain.settlementSettler.repository.SettlementSettlerRepository;
import com.umc.DutchTogether.domain.settler.dto.SettlerRequest;
import com.umc.DutchTogether.domain.settler.entity.Settler;
import com.umc.DutchTogether.domain.settler.repository.SettlerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class SettlerCommandServiceImpl implements SettlerCommandService {

    private final SettlerRepository settlerRepository;
    private final SettlementRepository settlementRepository;
    private final SettlementSettlerRepository settlementSettlerRepository;
    
    // 저장과정, find과정 예외처리 추가 예정
    @Override
    public Boolean createSettler(SettlerRequest.SettlerRequestDTO request) {
        List<SettlerRequest.SettlerSettlementsDTO> settlerSettlements = request.getRequests();
        settlerSettlements.stream()
                .map(dto -> {
                    Settler settler = Settler.builder()
                            .name(dto.getName())
                            .build();
                    settler= settlerRepository.save(settler);

                    return SettlementSettler.builder()
                            .settler(settler)
                            .settlement(settlementRepository.findById(dto.getSettlementId()).get())
                            .build();
                })
                .forEach(settlementSettlerRepository::save);
        return true;
    }
}
