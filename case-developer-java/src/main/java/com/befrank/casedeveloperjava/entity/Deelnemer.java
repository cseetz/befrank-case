package com.befrank.casedeveloperjava.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class Deelnemer {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String voornaam;

    private String tussenvoegsel;

    @Column(nullable = false)
    private String achternaam;

    @Column(nullable = false)
    private String straat;

    @Column(nullable = false)
    private Integer huisnummer;

    @Column(nullable = false)
    private String postcode;

    @Column(nullable = false)
    private String woonplaats;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate geboortedatum;

    @Column(unique = true, nullable = false)
    private String rekeningNummer;
}
