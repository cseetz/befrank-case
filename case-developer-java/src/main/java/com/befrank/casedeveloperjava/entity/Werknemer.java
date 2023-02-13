package com.befrank.casedeveloperjava.entity;

import lombok.Data;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class Werknemer {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Werkgever werkgever;

    @OneToOne
    @JoinColumn(referencedColumnName = "id")
    private Deelnemer deelnemer;
}
