package com.umc.DutchTogether.domain.settler.service;

import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlement.repository.SettlementRepository;
import com.umc.DutchTogether.domain.settlementSettler.entity.SettlementSettler;
import com.umc.DutchTogether.domain.settlementSettler.repository.SettlementSettlerRepository;
import com.umc.DutchTogether.domain.settler.converter.SettlerConverter;
import com.umc.DutchTogether.domain.settler.dto.SettlerRequest;
import com.umc.DutchTogether.domain.settler.entity.Settler;
import com.umc.DutchTogether.domain.settler.repository.SettlerRepository;
import com.umc.DutchTogether.global.apiPayload.exception.handler.SettlementHandler;
import com.umc.DutchTogether.global.apiPayload.exception.handler.SettlerHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.umc.DutchTogether.global.apiPayload.code.status.ErrorStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class SettlerCommandServiceImpl implements SettlerCommandService {

    private final SettlerRepository settlerRepository;
    private final SettlementRepository settlementRepository;
    private final SettlementSettlerRepository settlementSettlerRepository;
    
    // 저장과정, find 과정 예외처리 추가 예정
    @Override
    public Boolean createSettler(SettlerRequest.SettlerRequestDTO request) {
        List<SettlerRequest.SettlerSettlementsDTO> settlerSettlements = request.getRequests();
        if(settlerSettlements.isEmpty()){
            throw new SettlementHandler(SETTLEMENT_LIST_EMPTY);
        }
        settlerSettlements.stream()
                .map(dto -> {
                    Settler settler = SettlerConverter.toSettler(dto);
                    Optional<Settler> existingSettler = checkSettler(settler,request.getMeetingNum());
                    Settler finalSettler = existingSettler.orElseGet(() -> settlerRepository.save(settler));
                    Settlement settlement = settlementRepository.findById(dto.getSettlementId())
                            .orElseThrow(()->new SettlementHandler(SETTLEMENT_NOT_FOUND_ID));
                    int currentNum = settlement.getNumPeople();
                    settlement.setNumPeople(currentNum+1);
                    return SettlerConverter.toSettlementSettler(finalSettler, settlement);
                })
                .filter(Objects::nonNull) // null 아닌 경우에만 처리
                .forEach(settlementSettlerRepository::save);
        return true;
    }

    // 존재하지않아도 예외처리 X -> createSettler 에서 save
    public Optional<Settler> checkSettler(Settler settler,Long meetingNum) {
        return settlerRepository.findByMeetingIdAndSettlerName(meetingNum, settler.getName());
    }
}
