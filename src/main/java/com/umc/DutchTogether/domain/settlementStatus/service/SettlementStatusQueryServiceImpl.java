package com.umc.DutchTogether.domain.settlementStatus.service;

import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.meeting.repository.MeetingRepository;
import com.umc.DutchTogether.domain.payer.entity.Payer;
import com.umc.DutchTogether.domain.payer.repository.PayerRepository;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlement.repository.SettlementRepository;
import com.umc.DutchTogether.domain.settlementSettler.entity.SettlementSettler;
import com.umc.DutchTogether.domain.settlementSettler.repository.SettlementSettlerRepository;
import com.umc.DutchTogether.domain.settlementStatus.converter.SettlementStatusConverter;
import com.umc.DutchTogether.domain.settlementStatus.dto.SettlementStatusResponse;
import com.umc.DutchTogether.domain.settlementStatus.entity.SettlementStatus;
import com.umc.DutchTogether.domain.settlementStatus.respoistory.SettlementStatusRepository;
import com.umc.DutchTogether.domain.settlementSettler.entity.Status;

import com.umc.DutchTogether.global.apiPayload.code.status.ErrorStatus;
import com.umc.DutchTogether.global.apiPayload.exception.handler.MeetingHandler;
import com.umc.DutchTogether.global.apiPayload.exception.handler.PayerHandler;
import com.umc.DutchTogether.global.apiPayload.exception.handler.SettlementHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.umc.DutchTogether.domain.settlementStatus.converter.SettlementStatusConverter.settlementSettlersDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class SettlementStatusQueryServiceImpl implements SettlementStatusQueryService{
    //Get요청에 대한 비지니스 로직 처리

    private final SettlementStatusRepository settlementStatusRepository;
    private final SettlementRepository settlementRepository;
    private final MeetingRepository meetingRepository;
    private final PayerRepository payerRepository;
    private final SettlementSettlerRepository settlementSettlerRepository;

    //정산 현황 보기 page 정보 전달
    @Override
    public SettlementStatusResponse.SettlementStatusDTO getStatus(Long meetingNum) {
        Meeting meeting = meetingRepository.findById(meetingNum)
                .orElseThrow(() -> new MeetingHandler(ErrorStatus.MEETING_NOT_FOUND));

        List<Settlement> settlements = settlementRepository.findAllByMeetingId(meetingNum);

        if (settlements.isEmpty()) {
            throw new SettlementHandler(ErrorStatus.SETTLEMENT_NOT_FOUND_ID);
        }

        List<SettlementStatusResponse.SettlementListDTO> settlementDTOList = settlements.stream()
                .map(settlement -> {
                    SettlementStatus settlementStatus = settlementStatusRepository.findById(settlement.getSettlementStatus().getId())
                            .orElseThrow(() -> new SettlementHandler(ErrorStatus.SETTLEMENT_STATUS_NOT_FOUND));

                    List<SettlementStatusResponse.SettlementSettlersDTO> settlersDTOList = getSettlers(settlement.getId());

                    return SettlementStatusConverter.toSettlementListDTO(settlement, settlersDTOList);
                })
                .collect(Collectors.toList());

        return SettlementStatusConverter.toSettlementStatusDTO(meeting, settlementDTOList);
    }

    // 정산 완료 인원을 리스트에 dto(name,updateAt)로 담는 메소드
    public List<SettlementStatusResponse.SettlementSettlersDTO> getSettlers(Long settlementId){

        List<SettlementSettler> settlementSettlers = settlementSettlerRepository.findAllBySettlementIdAndStatus(settlementId, Status.COMPLETED);

        return settlementSettlers.stream()
                .map(settlementSettler -> settlementSettlersDTO(settlementSettler.getSettler()))
                .collect(Collectors.toList());
    }
}

