package com.umc.DutchTogether.domain.settler.repository;

import com.umc.DutchTogether.domain.settler.entity.Settler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettlerRepository extends JpaRepository<Settler, Long> {
}

