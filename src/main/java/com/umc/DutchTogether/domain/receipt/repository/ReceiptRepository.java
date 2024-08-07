package com.umc.DutchTogether.domain.receipt.repository;

import com.umc.DutchTogether.domain.receipt.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Long> {
}

