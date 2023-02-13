package com.befrank.casedeveloperjava.service;

import com.befrank.casedeveloperjava.model.Belegging;

import java.util.List;

public interface BeleggingService {

    /**
     * Verkrijgt een lijst van beleggingen op basis van de opgegeven rekeningnummer.
     *
     * @param rekeningNr
     * @return
     */
    List<Belegging> getBeleggingen(String rekeningNr);

    /**
     * Berekent de verwachte waarde van de pensioenspot op de opgegeven pensioenleeftijd.
     *
     * @param email
     * @param pensioenleeftijd
     * @return
     */
    Double getVerwachteWaarde(String email, Integer pensioenleeftijd);
}
