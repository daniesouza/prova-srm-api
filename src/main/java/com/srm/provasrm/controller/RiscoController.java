package com.srm.provasrm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srm.provasrm.model.Risco;

@RestController
@RequestMapping("/riscos")
public class RiscoController {
	
	
    @GetMapping
    public Risco[] findAll() {
        return Risco.values();
    }

}
