package com.befrank.casedeveloperjava.service;

import com.befrank.casedeveloperjava.entity.Deelnemer;
import com.befrank.casedeveloperjava.entity.Dienstverband;
import com.befrank.casedeveloperjava.exception.DeelnemerNotFoundException;
import com.befrank.casedeveloperjava.model.Belegging;
import com.befrank.casedeveloperjava.repository.DeelnemerRepository;
import com.befrank.casedeveloperjava.repository.DienstverbandRepository;
import com.befrank.casedeveloperjava.restclients.ExterneBeleggingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BeleggingServiceImpl implements BeleggingService {
    private final DeelnemerRepository deelnemerRepository;
    private final DienstverbandRepository dienstverbandRepository;
    private final ExterneBeleggingService externeBeleggingService;

    @Override
    public List<Belegging> getBeleggingen(String rekeningNr) {
        return externeBeleggingService.getBeleggingen(rekeningNr);
    }

    @Override
    public Double getVerwachteWaarde(String email, Integer pensioenleeftijd) {
        final Deelnemer deelnemer = deelnemerRepository.findByEmail(email).orElseThrow(() -> new DeelnemerNotFoundException(String.format("Deelnemer niet gevonden; email=%s", email)));

        final Double jaarlijkePremie = getJaarlijkePremie(deelnemer.getId());
        final Double huidigeWaarde = externeBeleggingService.somHuidigeBeleggingsWaarde(deelnemer.getRekeningNummer());
        final Double rendement = 3D;

        final Integer jarenTotPensioen = getJarenTotPensioen(deelnemer.getGeboortedatum(), pensioenleeftijd);

        return berekenWaardeInclusiefPremie(huidigeWaarde, jaarlijkePremie, rendement, jarenTotPensioen);
    }

    public Double getJaarlijkePremie(Long deelnemerId) {
        final Optional<Dienstverband> optionalDienstverband = dienstverbandRepository.findByWerknemer_Deelnemer_Id(deelnemerId);

        if (optionalDienstverband.isPresent()) {
            final Dienstverband dienstverband = optionalDienstverband.get();
            final Double premie = (dienstverband.getFulltimeSalaris() - dienstverband.getFranchise()) * waardeNaarPercentage(dienstverband.getParttimePercentage()) * waardeNaarPercentage(dienstverband.getPremiePercentage());
            return afrondenDouble(premie);
        } else {
            // Deelnemer uit dienst, dus geen jaarlijkse premie.
            return 0D;
        }
    }

    public Double afrondenDouble(Double waarde) {
        double afgerondeWaarde = waarde; // = 123.4567
        afgerondeWaarde = afgerondeWaarde * 100; // = 12345.67
        afgerondeWaarde = Math.round(afgerondeWaarde); // = 12346
        return afgerondeWaarde / 100; // = 123.46
    }

    private Double waardeNaarPercentage(Double waarde) {
        return waarde / 100;
    }

    public Double berekenWaardeInclusiefPremie(Double huidigeWaarde, Double jaarlijkePremie, Double rendement, Integer jarenTotPensioen) {
        return berekenPremieRecursief(huidigeWaarde, jaarlijkePremie, rendement, jarenTotPensioen, 0);
    }

    private Double berekenPremieRecursief(Double huidigeWaarde, Double jaarlijkePremie, Double rendement, Integer jarenTotPensioen, Integer teller) {
        if (teller >= jarenTotPensioen) {
            return huidigeWaarde;
        }
        teller++;
        final Double verwachteWaarde = huidigeWaarde + jaarlijkePremie + (huidigeWaarde + jaarlijkePremie / 2) * waardeNaarPercentage(rendement);
        final Double waarde = berekenPremieRecursief(verwachteWaarde, jaarlijkePremie, rendement, jarenTotPensioen, teller);
        return afrondenDouble(waarde);
    }

    public Integer getJarenTotPensioen(LocalDate geboortedatum, Integer pensioenleeftijd) {
        final int leeftijd = (int) ChronoUnit.YEARS.between(geboortedatum, LocalDate.now());
        return pensioenleeftijd - leeftijd;
    }
}
