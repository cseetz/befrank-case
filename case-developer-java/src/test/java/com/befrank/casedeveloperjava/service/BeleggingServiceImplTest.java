package com.befrank.casedeveloperjava.service;

import com.befrank.casedeveloperjava.TestDataHelper;
import com.befrank.casedeveloperjava.repository.DeelnemerRepository;
import com.befrank.casedeveloperjava.repository.DienstverbandRepository;
import com.befrank.casedeveloperjava.restclients.ExterneBeleggingService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BeleggingServiceImplTest {
    @InjectMocks
    private BeleggingServiceImpl beleggingService;
    @Mock
    private DeelnemerRepository deelnemerRepository;
    @Mock
    private DienstverbandRepository dienstverbandRepository;
    @Mock
    private ExterneBeleggingService externeBeleggingService;

    final static LocalDate GEBOORTEDATUM_LEEFTIJD_60 = LocalDate.of(1962, 12, 27);

    @Test
    void getVerwachteWaarde1Jaar() {
        when(deelnemerRepository.findByEmail(any(String.class))).thenReturn(Optional.of(TestDataHelper.createDeelnemer(GEBOORTEDATUM_LEEFTIJD_60)));
        when(dienstverbandRepository.findByWerknemer_Deelnemer_Id(any(Long.class))).thenReturn(Optional.of(TestDataHelper.createDienstverband()));
        when(externeBeleggingService.somHuidigeBeleggingsWaarde(any(String.class))).thenReturn(100000D);

        final Double waarde = beleggingService.getVerwachteWaarde("casper@voorbeeld.nl", 61);

        Assertions.assertThat(waarde).isEqualTo(104802.68D);
    }

    @Test
    void getVerwachteWaarde5Jaar() {
        when(deelnemerRepository.findByEmail(any(String.class))).thenReturn(Optional.of(TestDataHelper.createDeelnemer(GEBOORTEDATUM_LEEFTIJD_60)));
        when(dienstverbandRepository.findByWerknemer_Deelnemer_Id(any(Long.class))).thenReturn(Optional.of(TestDataHelper.createDienstverband()));
        when(externeBeleggingService.somHuidigeBeleggingsWaarde(any(String.class))).thenReturn(100000D);

        final Double waarde = beleggingService.getVerwachteWaarde("casper@voorbeeld.nl", 65);

        Assertions.assertThat(waarde).isEqualTo(125498.08D);
    }

    @Test
    void getJaarlijkePremie1() {
        when(dienstverbandRepository.findByWerknemer_Deelnemer_Id(any(Long.class))).thenReturn(Optional.of(TestDataHelper.createDienstverband(1L, 100000D, 50D, 20000D, 10D)));

        final Double premie = beleggingService.getJaarlijkePremie(1L);

        Assertions.assertThat(premie).isEqualTo(4000D); // = (100000 - 20000) * 0,5 * 0,1
    }

    @Test
    void getJaarlijkePremie2() {
        when(dienstverbandRepository.findByWerknemer_Deelnemer_Id(any(Long.class))).thenReturn(Optional.of(TestDataHelper.createDienstverband(1L, 60000D, 80D, 15599D, 5D)));

        final Double premie = beleggingService.getJaarlijkePremie(1L);

        Assertions.assertThat(premie).isEqualTo(1776.04D); // = (60000 - 15599) * 0,8 * 0,05
    }

    @Test
    void afrondenDoubleBoven() {
        final Double value = beleggingService.afrondenDouble(123.4567D);

        Assertions.assertThat(value).isEqualTo(123.46D);
    }

    @Test
    void afrondenDoubleBeneden() {
        final Double value = beleggingService.afrondenDouble(123.4549D);

        Assertions.assertThat(value).isEqualTo(123.45D);
    }

    @Test
    void berekenWaardeInclusiefPremie1Jaar() {
        final Double jaarlijksePremie = 1776.04D;
        final Double waarde = beleggingService.berekenWaardeInclusiefPremie(100000D, jaarlijksePremie, 3D, 1);

        Assertions.assertThat(waarde).isEqualTo(104802.68D);
    }

    @Test
    void berekenWaardeInclusiefPremie5Jaar() {
        final Double jaarlijksePremie = 1776.04D;
        final Double waarde = beleggingService.berekenWaardeInclusiefPremie(100000D, jaarlijksePremie, 3D, 5);

        Assertions.assertThat(waarde).isEqualTo(125498.08D);
    }

    @Test
    void getJarenTotPensioen1Jaar() {
        final Integer jarenTotPensioen = beleggingService.getJarenTotPensioen(GEBOORTEDATUM_LEEFTIJD_60, 61);

        Assertions.assertThat(jarenTotPensioen).isEqualTo(1);
    }

    @Test
    void getJarenTotPensioen5Jaar() {
        final Integer jarenTotPensioen = beleggingService.getJarenTotPensioen(GEBOORTEDATUM_LEEFTIJD_60, 65);

        Assertions.assertThat(jarenTotPensioen).isEqualTo(5);
    }
}
