package com.umc.DutchTogether.domain.payer.service;

import com.umc.DutchTogether.domain.payer.dto.PayerResponse;
import com.umc.DutchTogether.domain.payer.entity.Payer;
import com.umc.DutchTogether.domain.payer.repository.PayerRepository;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlement.repository.SettlementRepository;
import com.umc.DutchTogether.domain.settlementSettler.entity.SettlementSettler;
import com.umc.DutchTogether.domain.settlementSettler.repository.SettlementSettlerRepository;
import com.umc.DutchTogether.global.apiPayload.exception.handler.PayerHandler;
import com.umc.DutchTogether.global.apiPayload.exception.handler.SettlementHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.umc.DutchTogether.global.apiPayload.code.status.ErrorStatus.*;


@Service
@RequiredArgsConstructor
@Transactional
public class PayerQueryServiceImpl implements PayerQueryService{

    private final SettlementRepository settlementRepository;
    private final SettlementSettlerRepository settlementSettlerRepository;

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

    @Override
    public PayerResponse.PayerInfoListDTO getPayerInfoListDTO(Long settlerId){
        List<Settlement> settlementList = getSettlementListBySettlerId(settlerId);
        List<PayerResponse.PayerInfoDTO> infoList= settlementList.stream()
                .map(Settlement->{
                    Payer payer = Settlement.getPayer();
                    //IF 추가
                    return PayerResponse.PayerInfoDTO.builder()
                            .totalAmount(Settlement.getTotalAmount())
                            .bank(payer.getBank())
                            .accountNum(payer.getAccountNum())
                            .name(payer.getName())
                            .build();
                })
                .collect(Collectors.toList());
        return PayerResponse.PayerInfoListDTO.builder()
                .PayerInfos(infoList)
                .build();
    }

    public List<Settlement> getSettlementListBySettlerId(Long settlerId){
        List<SettlementSettler> settlementSettlers = settlementSettlerRepository.findAllBySettlerId(settlerId);
        return settlementSettlers.stream()
                .map(SettlementSettler->{
                    Settlement settlement = SettlementSettler.getSettlement();
                    if(settlement==null) {
                        throw new SettlementHandler(SETTLEMENT_NOT_FOUND_BY_SETTLER);
                    }
                    return settlement;
                })
                .collect(Collectors.toList());
    }
}

