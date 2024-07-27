package com.umc.DutchTogether.domain.payer.repository;

import com.umc.DutchTogether.domain.payer.entity.Payer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayerRepository extends JpaRepository<Payer, Long> {
}
