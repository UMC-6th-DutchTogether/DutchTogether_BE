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
                    Payer savedPayer = payerRepository.save(payer);
                    return PayerConverter.toPayerDTO(savedPayer);
                })
                .collect(Collectors.toList());

        return PayerResponse.PayerListDTO.builder()
                .payers(payersResponse)
                .build();
    }
}
