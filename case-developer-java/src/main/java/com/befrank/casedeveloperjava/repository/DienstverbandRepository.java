package com.befrank.casedeveloperjava.repository;

import com.befrank.casedeveloperjava.entity.Dienstverband;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DienstverbandRepository extends JpaRepository<Dienstverband, Long> {
    Optional<Dienstverband> findByWerknemer_Deelnemer_Id(Long deelnemerId);
}
