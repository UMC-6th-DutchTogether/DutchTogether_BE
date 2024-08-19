package com.umc.DutchTogether.domain.payer.service;

import com.umc.DutchTogether.domain.payer.converter.PayerConverter;
import com.umc.DutchTogether.domain.payer.dto.PayerResponse;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlement.repository.SettlementRepository;
import com.umc.DutchTogether.domain.settlementSettler.entity.SettlementSettler;
import com.umc.DutchTogether.domain.settlementSettler.repository.SettlementSettlerRepository;
import com.umc.DutchTogether.global.apiPayload.exception.handler.SettlementHandler;
import com.umc.DutchTogether.global.apiPayload.exception.handler.SettlementSettlerHandler;
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

    //해당 모임의 결제자 정보를 제공하는 메소드
    @Override
    public PayerResponse.PayerNameListDTO getPayerList(Long meetingNum) {
        List<Settlement> settlementList = getSettlementList(meetingNum);
        List<PayerResponse.PayerNameDTO> payerNames = settlementList.stream()
                .map(PayerConverter::toPayerNameDTO)
                .collect(Collectors.toList());
        return PayerConverter.toPayerNameListDTO(payerNames);
    }

    //정산하기 리스트를 받는 메소드
    public List<Settlement> getSettlementList(Long meetingNum) {
        List<Settlement> settlements = settlementRepository.findAllByMeetingId(meetingNum);
        if (settlements.isEmpty()) {
            throw new SettlementHandler(SETTLEMENT_NOT_FOUND_BY_MEETING);
        }
        return settlements;
    }

    @Override
    public PayerResponse.PayerInfoListDTO getPayerInfoListDTO(Long settlerId){
        List<Settlement> settlementList = getSettlementListBySettlerId(settlerId);
        // num_people이 0인 경우 예외를 던짐
        boolean hasInvalidSettlement = settlementList.stream()
                .anyMatch(settlement -> settlement.getNumPeople() == 0);
        if (hasInvalidSettlement) {
            throw new SettlementHandler(SETTLEMENT_NUM_PEOPLE_FORBIDDEN);
        }
        List<PayerResponse.PayerInfoDTO> infoList= settlementList.stream()
                .map(PayerConverter::toPayerInfoDTO)
                .collect(Collectors.toList());
        return PayerConverter.payerInfoListDTO(infoList);
    }

    //settlerId로 연결된 settlement를 모두 찾는 메소드
    public List<Settlement> getSettlementListBySettlerId(Long settlerId){
        List<SettlementSettler> settlementSettlers = settlementSettlerRepository.findAllBySettlerId(settlerId);
        if(settlementSettlers.isEmpty()){
            throw new SettlementSettlerHandler(SETTLEMENT_SETTLER_NOT_FOUND_BY_SETTLER);
        }
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

