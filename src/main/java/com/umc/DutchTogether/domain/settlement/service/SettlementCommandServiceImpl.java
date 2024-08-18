package com.umc.DutchTogether.domain.settlement.service;

import com.umc.DutchTogether.domain.meeting.entity.Meeting;
import com.umc.DutchTogether.domain.meeting.repository.MeetingRepository;
import com.umc.DutchTogether.domain.payer.entity.Payer;
import com.umc.DutchTogether.domain.payer.repository.PayerRepository;
import com.umc.DutchTogether.domain.receipt.entity.Receipt;
import com.umc.DutchTogether.domain.receipt.repository.ReceiptRepository;
import com.umc.DutchTogether.domain.settlement.converter.SettlementConverter;
import com.umc.DutchTogether.domain.settlement.dto.SettlementRequest;
import com.umc.DutchTogether.domain.settlement.dto.SettlementResponse;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlement.repository.SettlementRepository;
import com.umc.DutchTogether.domain.settlementStatus.entity.SettlementStatus;
import com.umc.DutchTogether.domain.settlementStatus.respoistory.SettlementStatusRepository;
import com.umc.DutchTogether.global.apiPayload.exception.handler.SettlementHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.umc.DutchTogether.global.apiPayload.code.status.ErrorStatus.SETTLEMENT_NOT_FOUND_ID;

@Service
@RequiredArgsConstructor
@Transactional
public class SettlementCommandServiceImpl implements SettlementCommandService {

    private final SettlementRepository settlementRepository;
    private final MeetingRepository meetingRepository;
    private final PayerRepository payerRepository;
    private final SettlementStatusRepository settlementStatusRepository;
    private final ReceiptRepository receiptRepository;

    @Override
    public SettlementResponse.SettlementDTO createSingleSettlement(SettlementRequest.SettlementDTO request) {
        Optional<Meeting> meeting = meetingRepository.findById(request.getMeetingNum());
        Receipt receipt = null;
        if (request.getReceiptId() != null) {
            receipt = receiptRepository.findById(request.getReceiptId()).orElse(null);
        }

        // 결제자 저장 ( 여유되면 converter에 다 넣자)
        Payer payer = Payer.builder()
                .name(request.getPayer())
                .accountNum(request.getAccountNumber())
                .bank(request.getBankName())
                .build();
        Settlement settlement = Settlement.builder()
                .meeting(meeting.get())
                .payer(payer)
                .totalAmount(request.getTotalAmount())
                .numPeople(request.getNumPeople())
                .receipt(receipt)
                .build();
        SettlementStatus settlementStatus = SettlementStatus.builder()
                .settlement(settlement)
                .build();

        payerRepository.save(payer);
        settlementRepository.save(settlement);
        settlementStatusRepository.save(settlementStatus);

        return SettlementConverter.toSettlementDTO(settlement);
    }

    @Override
    public Boolean updateSettlement(SettlementRequest.SettlementInfoListDTO request) {
        List<SettlementRequest.SettlementInfoDTO> infoList = request.getSettlementInfoList();

        infoList.forEach(dto -> {
            Settlement settlement = settlementRepository.findById(dto.getSettlementId())
                    .orElseThrow(() -> new SettlementHandler(SETTLEMENT_NOT_FOUND_ID));
            Receipt receipt = null;
            if (dto.getReceiptId() != null) {
                receipt = receiptRepository.findById(dto.getReceiptId()).orElse(null);
            }

            // 기존 엔티티 업데이트
            settlement.setItems(dto.getItem());
            settlement.setTotalAmount(dto.getTotalAmount());
            settlement.setReceipt(receipt);
            settlementRepository.save(settlement);

            SettlementStatus settlementStatus = SettlementStatus.builder()
                    .settlement(settlement)
                    .build();
            settlementStatusRepository.save(settlementStatus);
        });

        return true;
    }

    @Override
    public SettlementResponse.SettlementDTO createMultipleSettlement(SettlementRequest.SettlementIdDTO request) {
        return null;
    }

}
