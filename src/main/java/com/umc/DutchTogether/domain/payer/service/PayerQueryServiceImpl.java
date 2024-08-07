package com.umc.DutchTogether.domain.payer.service;

import com.umc.DutchTogether.domain.payer.dto.PayerResponse;
import com.umc.DutchTogether.domain.payer.entity.Payer;
import com.umc.DutchTogether.domain.payer.repository.PayerRepository;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlement.repository.SettlementRepository;
import com.umc.DutchTogether.global.apiPayload.exception.handler.PayerHandler;
import com.umc.DutchTogether.global.apiPayload.exception.handler.SettlementHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.umc.DutchTogether.global.apiPayload.code.status.ErrorStatus.Payer_NOT_FOUND_BY_SETTLEMENTID;
import static com.umc.DutchTogether.global.apiPayload.code.status.ErrorStatus.SETTLEMENT_NOT_FOUND_ID;


@Service
@RequiredArgsConstructor
@Transactional
public class PayerQueryServiceImpl implements PayerQueryService{

    private final SettlementRepository settlementRepository;

    @Override
    public PayerResponse.PayerNameListDTO getPayerList(Long meetingNum) {
        List<Settlement> settlementList = getSettlementList(meetingNum);
        List<PayerResponse.PayerNameDTO> payerNames = settlementList.stream()
                .map(settlement -> PayerResponse.PayerNameDTO.builder()
                        .name(settlement.getPayer().getName())
                        .settlementId(settlement.getId())
                        .build())
                .collect(Collectors.toList());

        return PayerResponse.PayerNameListDTO.builder()
                .names(payerNames)
                .build();
    }

    //정산하기 리스트를 받는 메소드
    public List<Settlement> getSettlementList(Long meetingNum) {
        List<Settlement> settlements = settlementRepository.findAllByMeetingId(meetingNum);
        if (settlements.isEmpty()) {
            throw new SettlementHandler(SETTLEMENT_NOT_FOUND_ID);
        }
        return settlements;
    }

//    //정산하기 id로 Payer를 찾는 메소드
//    public Payer getPayer(Long settlementId) {
//        return payerRepository.findById(settlementId)
//                .orElseThrow(() -> new PayerHandler(Payer_NOT_FOUND_BY_SETTLEMENTID));
//    }
}

