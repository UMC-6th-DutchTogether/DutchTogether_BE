package com.umc.DutchTogether.domain.payer.service;

import com.umc.DutchTogether.domain.payer.entity.Payer;
import com.umc.DutchTogether.domain.payer.repository.PayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PayerService {

    private final PayerRepository payerRepository;


    public PayerService(PayerRepository payerRepository) {
        this.payerRepository = payerRepository;
    }

}
