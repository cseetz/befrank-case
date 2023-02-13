package com.befrank.casedeveloperjava.entity;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class Dienstverband {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Double fulltimeSalaris;

    @Column(nullable = false)
    private Double parttimePercentage;

    @Column(nullable = false)
    private Double franchise;

    @Column(nullable = false)
    private Double premiePercentage;

    @OneToOne(optional = false)
    @JoinColumn(referencedColumnName = "id")
    private Werknemer werknemer;

    @OneToOne(optional = false)
    @JoinColumn(referencedColumnName = "id")
    private Werkgever werkgever;
}
