package com.umc.DutchTogether.domain.settlement.service;

import com.umc.DutchTogether.domain.settlement.repository.SettlementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SettlementCommandServiceImpl implements SettlementCommandService {

    private final SettlementRepository settlementRepository;



}
