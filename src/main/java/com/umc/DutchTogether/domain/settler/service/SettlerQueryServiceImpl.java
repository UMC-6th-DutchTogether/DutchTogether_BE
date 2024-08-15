package com.umc.DutchTogether.domain.settler.service;

import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.meeting.repository.MeetingRepository;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlement.repository.SettlementRepository;
import com.umc.DutchTogether.domain.settlementSettler.entity.SettlementSettler;
import com.umc.DutchTogether.domain.settlementSettler.repository.SettlementSettlerRepository;
import com.umc.DutchTogether.domain.settler.converter.SettlerConverter;
import com.umc.DutchTogether.domain.settler.dto.SettlerResponse;
import com.umc.DutchTogether.domain.settler.entity.Settler;
import com.umc.DutchTogether.domain.settler.repository.SettlerRepository;
import com.umc.DutchTogether.global.apiPayload.exception.handler.MeetingHandler;
import com.umc.DutchTogether.global.apiPayload.exception.handler.SettlementHandler;
import com.umc.DutchTogether.global.apiPayload.exception.handler.SettlerHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.umc.DutchTogether.global.apiPayload.code.status.ErrorStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class SettlerQueryServiceImpl implements SettlerQueryService {

    private final SettlementRepository settlementRepository;
    private final SettlementSettlerRepository settlementSettlerRepository;
    private final SettlerRepository settlerRepository;
    private final MeetingRepository meetingRepository;

    @Override
    public List<Settlement> getSettlements(Long meetingNum) {
        List<Settlement> settlements = settlementRepository.findAllByMeetingId(meetingNum);
        if (settlements.isEmpty()) {
            throw new SettlementHandler(SETTLEMENT_NOT_FOUND_BY_MEETING);
        }
        return settlements;
    }

    @Override
    public SettlerResponse.settlerResponseDTO createSettlers(String Link) {
        Meeting meeting = meetingRepository.findByLink(Link).orElseThrow(()-> new MeetingHandler(MEETING_NOT_FOUND));
        Long meetingNum = meeting.getId();;
        //n:m 매핑이므로 중복이 있을 수 있음.
        List<Settlement> settlements =getSettlements(meetingNum);
        //SettlerId를 중복 없이 가져옴
        Set<Long> uniqueSettlerIds = settlements.stream()
                .flatMap(settlement -> settlementSettlerRepository.findAllSettlerIdBySettlementId(settlement.getId()).stream())
                .collect(Collectors.toSet());
        //중복 없는 settler 리스트
        List<SettlerResponse.settlerDTO> settlerList = uniqueSettlerIds.stream()
                .map(settlerId->{
                    Settler settler = settlerRepository.findById(settlerId)
                                .orElseThrow(() -> new SettlerHandler(SETTLER_NOT_FOUND_BY_NAME));
                    return SettlerConverter.toSettlerDTO(settler);
                })
                .collect(Collectors.toList());

        return SettlerConverter.toSettlerResponseDTO(settlerList);
    }
}