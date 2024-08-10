package com.umc.DutchTogether.domain.settlementStatus.service;

import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.meeting.repository.MeetingRepository;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlementSettler.entity.SettlementSettler;
import com.umc.DutchTogether.domain.settlementSettler.entity.Status;
import com.umc.DutchTogether.domain.settlementSettler.repository.SettlementSettlerRepository;
import com.umc.DutchTogether.domain.settlementStatus.converter.SettlementStatusConverter;
import com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusRequest;
import com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusResponse;
import com.umc.DutchTogether.domain.settlementStatus.entity.SettlementStatus;
import com.umc.DutchTogether.domain.settlementStatus.respoistory.SettlementStatusRepository;
import com.umc.DutchTogether.domain.settler.entity.Settler;
import com.umc.DutchTogether.domain.settler.repository.SettlerRepository;
import com.umc.DutchTogether.global.apiPayload.code.status.ErrorStatus;
import com.umc.DutchTogether.global.apiPayload.exception.handler.SettlementHandler;
import com.umc.DutchTogether.global.apiPayload.exception.handler.SettlementStatusHandler;
import com.umc.DutchTogether.global.apiPayload.exception.handler.SettlerHandler;
import com.umc.DutchTogether.global.security.TokenProvider;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.umc.DutchTogether.global.apiPayload.code.status.ErrorStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class SettlementStatusCommandServiceImpl implements SettlementStatusCommandService{
    //Get제외 모든 요청에 대한 비지니스 처리 로그인

    private final SettlementStatusRepository settlementStatusRepository;
    private final SettlerRepository settlerRepository;
    private final MeetingRepository meetingRepository;
    private final TokenProvider tokenProvider;
    private final SettlementSettlerRepository settlementSettlerRepository;

    @Override
    public SettlementStatusResponse.SettlementStatusLoginDTO login(SettlementStatusRequest.SettlementStatusDTO request) {

        Optional<Meeting> meetingOptional = meetingRepository.findByMeetingIdAndPassword(request.getMeetingId(), request.getPassword());
        if (meetingOptional.isEmpty()) {
             throw new SettlementHandler(ErrorStatus.MEETING_NOT_FOUND);
        }

        Meeting meeting = meetingOptional.get();
        String token = tokenProvider.createToken(meeting);

        return SettlementStatusConverter.toLoginResultDTO(meeting.getId(), token);
    }

    @Override
    public Boolean addSettler(SettlementStatusRequest.settlerInfo settlerInfo) {

        //정산현황 테이블
        SettlementStatus settlementStatus = settlementStatusRepository.findBySettlementId(settlerInfo.getSettlementId())
                .orElseThrow(() -> new SettlementStatusHandler(SETTLEMENT_STATUS_NOT_FOUND));
        //해당 정산자
        Settler settler = settlerRepository.findByName(settlerInfo.getName())
                .orElseThrow(() -> new SettlerHandler(SETTLER_NOT_FOUND_BY_NAME));
        //입력받은 정산자와 정산하기로 찾은 정산자-정산하기
        SettlementSettler settlementSettler = settlementSettlerRepository.findBySettlementIdAndSettlerName(settlerInfo.getSettlementId(),settlerInfo.getName())
                .orElseThrow(()-> new SettlementStatusHandler(SETTLEMENT_STATUS_NOT_FOUND));

        settlementSettler.setStatus(Status.COMPLETED);
        settlementSettlerRepository.save(settlementSettler);

        return true;
    }

}
