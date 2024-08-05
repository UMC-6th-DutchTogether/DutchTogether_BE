package com.umc.DutchTogether.domain.settlementSettler.repository;

import com.umc.DutchTogether.domain.settlementSettler.entity.SettlementSettler;
import com.umc.DutchTogether.domain.settlementSettler.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SettlementSettlerRepository extends JpaRepository<SettlementSettler, Long> {
    List<SettlementSettler> findAllBySettlementIdAndStatus(Long settlementId, Status status);
}
