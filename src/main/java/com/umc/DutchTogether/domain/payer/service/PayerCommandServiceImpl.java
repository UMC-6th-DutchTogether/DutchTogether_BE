package com.umc.DutchTogether.domain.payer.service;

import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.meeting.repository.MeetingRepository;
import com.umc.DutchTogether.domain.payer.converter.PayerConverter;
import com.umc.DutchTogether.domain.payer.dto.PayerRequest;
import com.umc.DutchTogether.domain.payer.dto.PayerResponse;
import com.umc.DutchTogether.domain.payer.entity.Payer;
import com.umc.DutchTogether.domain.payer.repository.PayerRepository;
import com.umc.DutchTogether.domain.settlement.converter.SettlementConverter;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlement.repository.SettlementRepository;
import com.umc.DutchTogether.global.apiPayload.exception.handler.MeetingHandler;
import com.umc.DutchTogether.global.apiPayload.exception.handler.PayerHandler;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.umc.DutchTogether.global.apiPayload.code.status.ErrorStatus.*;

@Service
@RequiredArgsConstructor
@Transactional
public class PayerCommandServiceImpl implements PayerCommandService{

    private final PayerRepository payerRepository;
    private final SettlementRepository settlementRepository;
    private final MeetingRepository meetingRepository;
    
    //PayerListDTO 전달받아 해당 모임의 Payers 저장하는 메소드
    @Override
    public PayerResponse.PayerListDTO updatePayer(PayerRequest.PayerListDTO request) {
        List<PayerRequest.PayerDTO> payers = request.getPayers();
        // 입력 확인
        if(payers.isEmpty()){
            throw new PayerHandler(PAYER_LIST_EMPTY);
        }
        List<PayerResponse.PayerDTO> payersResponse = payers.stream()
                .map(payerDTO-> {
                    Payer payer = payerRepository.findById(payerDTO.getPayerId())
                            .orElseThrow(()-> new PayerHandler(PAYER_LIST_NOT_FOUND));
                    //payer 업데이트
                    payer.setBank(payerDTO.getBank());
                    payer.setAccountNum(payerDTO.getAccount());
                    Payer savedPayer = payerRepository.save(payer);
                    return PayerConverter.toPayerDTO(savedPayer);
                })
                .collect(Collectors.toList());
        return PayerConverter.payerListDTO(payersResponse);
    }

    // 결제자 이름을 받고 결제자 생성
    @Override
    public PayerResponse.PayerListDTO createPayer(PayerRequest.PayerNameListDTO request) {
        List<PayerRequest.PayerNameDTO> payers = request.getPayerNames();
        //입력 확인
        if(payers.isEmpty()){
            throw new PayerHandler(PAYER_LIST_EMPTY);
        }
        List<PayerResponse.PayerDTO> payerResponse = payers.stream()
                .map(payerDTO->{
                    Payer payer = PayerConverter.toPayer(payerDTO);
                    Optional<Payer> existPayer = checkPayer(payer);
                    //결제자가 2명인 경우
                    if (existPayer.isPresent()) {
                        //정산하기만 추가적으로 생성, 결제자는 1명으로 유지
                        createSettlement(existPayer.get(), request.getMeetingNum());
                        return null;
                    }
                    Payer savedPayer = saveNewPayer(payer);
                    createSettlement(savedPayer,request.getMeetingNum());
                    return PayerConverter.toPayerDTO(savedPayer);
                })
                .filter(Objects::nonNull) // null 값을 필터링하여 제거
                .toList();
        return PayerConverter.payerListDTO(payerResponse);
    }

    //정산하기 생성 메소드
    private void createSettlement(Payer payer, Long meetingNum) {
        Meeting meeting = meetingRepository.findById(meetingNum)
                .orElseThrow(() -> new MeetingHandler(MEETING_NOT_FOUND));
        Settlement settlement = SettlementConverter.toSettlement(payer,meeting);
        try {
            settlementRepository.save(settlement);
        } catch (Exception e) {
            throw new RuntimeException("Settlement 저장에 실패 했습니다.", e);
        }
    }

    // 새로은 payer 저장 메소드
    private Payer saveNewPayer(Payer payer) {
        try {
            return payerRepository.save(payer);
        } catch (Exception e) {
            throw new RuntimeException("Payer 저장에 실패 했습니다.", e);
        }
    }

    // 해당 결제자가 이미 있는지 확인
    public Optional<Payer> checkPayer(Payer payer) {
        return payerRepository.findByNameAndAccountNumAndBank(
                payer.getName(), payer.getAccountNum(), payer.getBank());
    }
}
