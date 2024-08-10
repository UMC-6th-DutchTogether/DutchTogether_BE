package com.umc.DutchTogether.domain.settlementStatus.service;

import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.meeting.repository.MeetingRepository;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlementSettler.entity.SettlementSettler;
import com.umc.DutchTogether.domain.settlementStatus.converter.SettlementStatusConverter;
import com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusRequest;
import com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusResponse;
import com.umc.DutchTogether.domain.settlementStatus.entity.SettlementStatus;
import com.umc.DutchTogether.domain.settlementStatus.respoistory.SettlementStatusRepository;
import com.umc.DutchTogether.domain.settler.entity.Settler;
import com.umc.DutchTogether.domain.settler.repository.SettlerRepository;
import com.umc.DutchTogether.global.apiPayload.code.status.ErrorStatus;
import com.umc.DutchTogether.global.apiPayload.exception.handler.SettlementHandler;
import com.umc.DutchTogether.global.security.TokenProvider;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.umc.DutchTogether.global.apiPayload.code.status.ErrorStatus.MEETING_NOT_FOUND;

@Service
@RequiredArgsConstructor
@Transactional
public class SettlementStatusCommandServiceImpl implements SettlementStatusCommandService{
    //Get제외 모든 요청에 대한 비지니스 처리 로그인

    private final SettlementStatusRepository settlementStatusRepository;
    private final SettlerRepository settlerRepository;
    private final MeetingRepository meetingRepository;
    private final TokenProvider tokenProvider;

    @Override
    public SettlementStatusResponse.SettlementStatusLoginDTO login(SettlementStatusRequest.SettlementStatusDTO request) {

        Optional<Meeting> meetingOptional = meetingRepository.findByMeetingIdAndPassword(request.getMeetingId(), request.getPassword());
        if (!meetingOptional.isPresent()) {
             throw new SettlementHandler(ErrorStatus.MEETING_NOT_FOUND);
        }

        Meeting meeting = meetingOptional.get();
        String token = tokenProvider.createToken(meeting);

        return SettlementStatusConverter.toLoginResultDTO(meeting.getId(), token);
    }

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
