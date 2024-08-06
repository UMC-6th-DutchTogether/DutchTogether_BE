package com.umc.DutchTogether.domain.payer.service;

import com.umc.DutchTogether.domain.payer.converter.PayerConverter;
import com.umc.DutchTogether.domain.payer.dto.PayerRequest;
import com.umc.DutchTogether.domain.payer.dto.PayerResponse;
import com.umc.DutchTogether.domain.payer.entity.Payer;
import com.umc.DutchTogether.domain.payer.repository.PayerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PayerCommandServiceImpl implements PayerCommandService{

    private final PayerRepository payerRepository;

    @Override
    public PayerResponse.PayerListDTO createPayer(PayerRequest.PayerListDTO request) {
        List<PayerRequest.PayerDTO> payers = request.getPayers();
        List<PayerResponse.PayerDTO> payersResponse = payers.stream()
                .map(payerDTO-> {
                    Payer  payer = PayerConverter.toPayer(payerDTO);
                    if (checkPayer(payer)){
                        return null;
                    }
                    Payer savedPayer = payerRepository.save(payer);
                    return PayerConverter.toPayerDTO(savedPayer);
                })
                .filter(Objects::nonNull) // null 값을 필터링하여 제거
                .collect(Collectors.toList());

        return PayerResponse.PayerListDTO.builder()
                .payers(payersResponse)
                .build();
    }

    //해당 결제자가 이미 있는 경우 (한 결제자가 2개 이상의 정산하기와 연결되어 있는 경우)
    public boolean checkPayer(Payer payer) {
        return payerRepository.existsByNameAndAccountNumAndBank(
                payer.getName(), payer.getAccountNum(), payer.getBank());
    }
}
