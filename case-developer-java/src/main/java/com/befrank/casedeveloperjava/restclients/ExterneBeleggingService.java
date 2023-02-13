package com.befrank.casedeveloperjava.restclients;

import com.befrank.casedeveloperjava.exception.RekeningNotFoundException;
import com.befrank.casedeveloperjava.model.Belegging;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class ExterneBeleggingService {
    public List<Belegging> getBeleggingen(String rekeningNr) {
        // Note: This is mock data
        if (Objects.equals(rekeningNr, "rekening-1")) {
            return List.of(
                    createBelegging(1L, "fonds-1", 25000D),
                    createBelegging(2L, "fonds-2", 70000D),
                    createBelegging(3L, "fonds-3", 5000D)
            );
        } else {
            throw new RekeningNotFoundException(String.format("Rekening niet gevonden; rekening=%s", rekeningNr));
        }
    }

    private Belegging createBelegging(Long id, String naam, Double waarde) {
        Belegging belegging = new Belegging();
        belegging.setId(id);
        belegging.setFonds(naam);
        belegging.setWaarde(waarde);
        return belegging;
    }

    public Double somHuidigeBeleggingsWaarde(String rekeningNr) {
        return getBeleggingen(rekeningNr).stream().map(Belegging::getWaarde).mapToDouble(value -> value).sum();
    }
}
