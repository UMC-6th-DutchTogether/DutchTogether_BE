package com.umc.DutchTogether.domain.payer.service;

import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.meeting.repository.MeetingRepository;
import com.umc.DutchTogether.domain.payer.converter.PayerConverter;
import com.umc.DutchTogether.domain.payer.dto.PayerRequest;
import com.umc.DutchTogether.domain.payer.dto.PayerResponse;
import com.umc.DutchTogether.domain.payer.entity.Payer;
import com.umc.DutchTogether.domain.payer.repository.PayerRepository;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlement.repository.SettlementRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PayerCommandServiceImpl implements PayerCommandService{

    private final PayerRepository payerRepository;
    private final SettlementRepository settlementRepository;
    private final MeetingRepository meetingRepository;

    /*
    ##예외처리 바꿀 예정##
     */
    @Override
    public PayerResponse.PayerListDTO createPayer(PayerRequest.PayerListDTO request) {
        List<PayerRequest.PayerDTO> payers = request.getPayers();
        List<PayerResponse.PayerDTO> payersResponse = payers.stream()
                .map(payerDTO-> {
                    Payer  payer = PayerConverter.toPayer(payerDTO);
                    Optional<Payer> existingPayer = checkPayer(payer);
                    if (existingPayer.isPresent()) {
                        createSettlement(existingPayer.get(), request.getMeetingNum());
                        return null;
                    }
                    Payer savedPayer = saveNewPayer(payer);
                    createSettlement(savedPayer,request.getMeetingNum());
                    return PayerConverter.toPayerDTO(savedPayer);
                })
                .filter(Objects::nonNull) // null 값을 필터링하여 제거
                .collect(Collectors.toList());
        return PayerResponse.PayerListDTO.builder()
                .payers(payersResponse)
                .build();
    }

    //정산하기 생성 메소드
    private void createSettlement(Payer payer, Long meetingNum) {
        Meeting meeting;
        try {
            meeting = meetingRepository.findById(meetingNum).orElseThrow(() -> new RuntimeException("Meeting not found with id: " + meetingNum));
        } catch (Exception e) {
            throw new RuntimeException("Failed to find meetingNum", e);
        }
        Settlement settlement = Settlement.builder()
                .meeting(meeting)
                .payer(payer)
                .build();
        try {
            settlementRepository.save(settlement);
        } catch (Exception e) {
            // 예외 처리 로직 추가
            throw new RuntimeException("Failed to save settlement", e);
        }
    }

    // 새로은 payer 저장 메소드
    private Payer saveNewPayer(Payer payer) {
        try {
            return payerRepository.save(payer);
        } catch (Exception e) {
            // 예외 처리 로직 추가
            throw new RuntimeException("Failed to save new payer", e);
        }
    }

    // 해당 결제자가 이미 있는지 확인
    public Optional<Payer> checkPayer(Payer payer) {
        return payerRepository.findByNameAndAccountNumAndBank(
                payer.getName(), payer.getAccountNum(), payer.getBank());
    }
}
