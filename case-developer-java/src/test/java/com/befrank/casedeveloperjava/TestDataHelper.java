package com.befrank.casedeveloperjava;

import com.befrank.casedeveloperjava.entity.Deelnemer;
import com.befrank.casedeveloperjava.entity.Dienstverband;

import java.time.LocalDate;

public class TestDataHelper {

    public static Dienstverband createDienstverband() {
        Dienstverband dienstverband = new Dienstverband();
        dienstverband.setId(1L);
        dienstverband.setFulltimeSalaris(60000D);
        dienstverband.setParttimePercentage(80D);
        dienstverband.setFranchise(15599D);
        dienstverband.setPremiePercentage(5D);
        return dienstverband;
    }

    public static Dienstverband createDienstverband(Long id, Double fulltimeSalaris, Double parttimePercentage, Double franchise, Double premiePercentage) {
        Dienstverband dienstverband = new Dienstverband();
        dienstverband.setId(id);
        dienstverband.setFulltimeSalaris(fulltimeSalaris);
        dienstverband.setParttimePercentage(parttimePercentage);
        dienstverband.setFranchise(franchise);
        dienstverband.setPremiePercentage(premiePercentage);
        return dienstverband;
    }

    public static Deelnemer createDeelnemer(LocalDate geboortedatum) {
        Deelnemer deelnemer = new Deelnemer();
        deelnemer.setId(1L);
        deelnemer.setVoornaam("Casper");
        deelnemer.setGeboortedatum(geboortedatum);
        deelnemer.setRekeningNummer("rekening-1");
        return deelnemer;
    }
}
