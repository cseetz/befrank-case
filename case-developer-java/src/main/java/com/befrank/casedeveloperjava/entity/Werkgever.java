package com.befrank.casedeveloperjava.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
public class Werkgever {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String naam;

    @OneToMany(mappedBy = "werkgever", cascade = CascadeType.ALL)
    private List<Werknemer> werknemers = new ArrayList<>();
}
