package com.analisis.proyecto.controller;

import java.util.List;
import java.time.LocalDateTime;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analisis.proyecto.entity.SaldoCuenta;
import com.analisis.proyecto.repository.SaldoCuentaRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/saldoCuenta")
@CrossOrigin
public class SaldoCuentaController {

    @Autowired
    SaldoCuentaRepository saldoCuentaRepository;

    @GetMapping("/buscar")
    public List<SaldoCuenta> getBuscar(@RequestParam(required = false) String param) {
        return saldoCuentaRepository.findAll();
    }

    @GetMapping("/buscar/{idsaldocuenta}")
    public ResponseEntity<SaldoCuenta> getSaldoCuentaPorId(@PathVariable("idsaldocuenta") int idsaldocuenta) {
        SaldoCuenta saldoCuenta = saldoCuentaRepository.findById(idsaldocuenta)
                .orElseThrow(() -> new EntityNotFoundException("Saldo no encontrada con id: " + idsaldocuenta));
        return ResponseEntity.ok(saldoCuenta);
    }
    
    @GetMapping("/listar")
    public List<SaldoCuenta> listarSaldoCuenta() {
    return saldoCuentaRepository.findAll(); 
    }

    @PostMapping("/guardar")
    public SaldoCuenta postGuardar(@RequestBody SaldoCuenta saldocuenta) {
        saldocuenta.setUsuariocreacion("Administrador");
        saldocuenta.setFechacreacion(LocalDateTime.now());
        return saldoCuentaRepository.save(saldocuenta);
    }

    @PutMapping("/actualizar/{idsaldocuenta}")
    public ResponseEntity<SaldoCuenta> actualizarSaldoCuenta(@PathVariable("idsaldocuenta") Integer idsaldocuenta, @RequestBody SaldoCuenta saldocuentaActualizado) {
        SaldoCuenta saldocuentaExistente = saldoCuentaRepository.findById(idsaldocuenta)
            .orElseThrow(() -> new EntityNotFoundException("Saldo cuenta no encontrado con id: " + idsaldocuenta));

            saldocuentaExistente.setSaldoanterior(saldocuentaActualizado.getSaldoanterior());
            saldocuentaExistente.setDebitos(saldocuentaActualizado.getDebitos());
            saldocuentaExistente.setCreditos(saldocuentaActualizado.getCreditos());
            saldocuentaExistente.setUsuariomodificacion("Administrador");
            saldocuentaExistente.setFechamodificacion(LocalDateTime.now());

    SaldoCuenta saldocuentaGuardada = saldoCuentaRepository.save(saldocuentaExistente);
    return ResponseEntity.ok(saldocuentaGuardada);
}

    @DeleteMapping("/eliminar/{idsaldocuenta}")
    public void eliminar(@PathVariable("idsaldocuenta") Integer idsaldocuenta) {
        saldoCuentaRepository.deleteById(idsaldocuenta);
    }
}