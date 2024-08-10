package com.umc.DutchTogether.domain.settlementStatus.respoistory;

import com.umc.DutchTogether.domain.settlementStatus.entity.SettlementStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SettlementStatusRepository extends JpaRepository<SettlementStatus, Long> {
    Optional<SettlementStatus> findBySettlementId(Long settlementId);
}
