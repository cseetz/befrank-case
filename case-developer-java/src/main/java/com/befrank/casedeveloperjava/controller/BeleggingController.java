package com.befrank.casedeveloperjava.controller;

import com.befrank.casedeveloperjava.model.BeleggingDto;
import com.befrank.casedeveloperjava.service.BeleggingService;
import com.befrank.casedeveloperjava.utils.BeleggingMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/case")
public class BeleggingController {
    private final BeleggingService beleggingService;

    @GetMapping("/rekening/{rekeningNr}")
    public List<BeleggingDto> getBeleggingen(@PathVariable("rekeningNr") String rekeningNr) {
        return beleggingService.getBeleggingen(rekeningNr).stream()
                .map(BeleggingMapper::toBeleggingDto)
                .toList();
    }

    @GetMapping("/waarde/{email}/{pensioenleeftijd}")
    public Double getVerwachteWaarde(@PathVariable("email") String email, @PathVariable("pensioenleeftijd") Integer pensioenleeftijd) {
        return beleggingService.getVerwachteWaarde(email, pensioenleeftijd);
    }
}
