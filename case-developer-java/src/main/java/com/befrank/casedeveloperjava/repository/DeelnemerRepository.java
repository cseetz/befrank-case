package com.befrank.casedeveloperjava.repository;

import com.befrank.casedeveloperjava.entity.Deelnemer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeelnemerRepository extends JpaRepository<Deelnemer, Long> {
    Optional<Deelnemer> findByEmail(String email);
}
