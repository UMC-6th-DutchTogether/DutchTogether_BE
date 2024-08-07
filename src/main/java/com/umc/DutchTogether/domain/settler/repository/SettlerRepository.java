package com.umc.DutchTogether.domain.settler.repository;

import com.umc.DutchTogether.domain.settler.entity.Settler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SettlerRepository extends JpaRepository<Settler, Long> {
    Optional<Settler> findByName(String settlerName);
    @Query("SELECT s FROM Settler s WHERE s.name = :name AND s.id IN (SELECT ss.settler.id FROM SettlementSettler ss WHERE ss.settlement.meeting.id = :meetingId)")
    Optional<Settler> findByMeetingIdAndSettlerName(@Param("meetingId") Long meetingId, @Param("name") String name);
}

