package com.befrank.casedeveloperjava.controller;

import com.befrank.casedeveloperjava.repository.DeelnemerRepository;
import com.befrank.casedeveloperjava.repository.DienstverbandRepository;
import com.befrank.casedeveloperjava.service.BeleggingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeleggingController.class)
class BeleggingControllerTest {
    @MockBean
    private BeleggingService beleggingService;
    @Autowired
    private MockMvc mvc;
    @MockBean
    private DeelnemerRepository deelnemerRepository;
    @MockBean
    private DienstverbandRepository dienstverbandRepository;

    @Test
    void getBeleggingen() throws Exception {
        when(beleggingService.getBeleggingen(any(String.class))).thenReturn(Collections.emptyList());

        mvc.perform(get("/case/rekening/rekening-1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getVerwachteWaarde() throws Exception {
        when(beleggingService.getVerwachteWaarde(any(String.class), any(Integer.class))).thenReturn(0D);

        mvc.perform(get("/case/waarde/casper@voorbeeld.nl/51")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}