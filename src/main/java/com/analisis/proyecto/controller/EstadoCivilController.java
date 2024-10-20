package com.analisis.proyecto.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.analisis.proyecto.entity.EstadoCivil;
import com.analisis.proyecto.repository.EstadoCivilRepository;



@RestController
@RequestMapping("/estadocivil")
@CrossOrigin

public class EstadoCivilController {
    @Autowired
    EstadoCivilRepository estadocivilRepository;

    @GetMapping("/buscar")
    public List<EstadoCivil> getBuscar(@RequestParam(required = false) String param) {
        return estadocivilRepository.findAll();
    }
    
     }