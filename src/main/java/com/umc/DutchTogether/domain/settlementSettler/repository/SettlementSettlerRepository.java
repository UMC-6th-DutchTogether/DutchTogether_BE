package com.umc.DutchTogether.domain.settlementSettler.repository;

import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import com.umc.DutchTogether.domain.settlementSettler.entity.SettlementSettler;
import com.umc.DutchTogether.domain.settlementSettler.entity.Status;
import com.umc.DutchTogether.domain.settler.entity.Settler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SettlementSettlerRepository extends JpaRepository<SettlementSettler, Long> {
    List<SettlementSettler> findAllBySettlementIdAndStatus(Long settlementId, Status status);
    Optional<SettlementSettler> findBySettlementAndSettler(Settlement settlement, Settler settler);
    boolean existsBySettlementIdAndSettlerName(Long settlementId, String settlerName);

    @Query("SELECT ss.settler.id FROM SettlementSettler ss WHERE ss.settlement.id = :settlementId")
    List<Long> findAllSettlerIdBySettlementId(@Param("settlementId") Long settlementId);
}
