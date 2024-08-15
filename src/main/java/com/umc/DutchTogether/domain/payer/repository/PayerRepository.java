package com.umc.DutchTogether.domain.payer.repository;

import com.umc.DutchTogether.domain.payer.entity.Payer;
import com.umc.DutchTogether.domain.settlement.entity.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PayerRepository extends JpaRepository<Payer, Long> {

      List<Payer> findAllByName(String name);
}
