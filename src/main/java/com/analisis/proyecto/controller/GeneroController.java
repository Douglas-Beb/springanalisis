package com.analisis.proyecto.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analisis.proyecto.entity.Genero;
import com.analisis.proyecto.repository.GeneroRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/genero")
@CrossOrigin
public class GeneroController {

    @Autowired
    GeneroRepository generoRepository;

    @GetMapping("/buscar")
    public List<Genero> getBuscar(@RequestParam(required = false) String param) {
        return generoRepository.findAll();
    }

    @GetMapping("/buscar/{idgenero}")
    public ResponseEntity<Genero> getGeneroPorId(@PathVariable("idgenero") int idgenero) {
        Genero genero = generoRepository.findById(idgenero)
                .orElseThrow(() -> new EntityNotFoundException("Genero no encontrada con id: " + idgenero));
        return ResponseEntity.ok(genero);
    }

    @DeleteMapping("/eliminar/{idgenero}")
    public void eliminar(@PathVariable("idgenero") int idgenero) {
        generoRepository.deleteById(idgenero);
    }

    @PostMapping("/guardar")
    public Genero postGuardar(@RequestBody Genero genero) {
        genero.setUsuariocreacion("System");
        genero.setFechacreacion(LocalDateTime.now());
        return generoRepository.save(genero);
    }

    @PutMapping("/actualizar/{idgenero}")
    public ResponseEntity<Genero> actualizarGenero(@PathVariable("idgenero") int idgenero, @RequestBody Genero generoActualizada) {
        Genero generoExistente = generoRepository.findById(idgenero)
                .orElseThrow(() -> new EntityNotFoundException("Genero no encontrada con id: " + idgenero));
    
        // Actualizar los campos necesarios
        generoExistente.setNombre(generoActualizada.getNombre());
        generoExistente.setUsuariomodificacion("Administrador");
        generoExistente.setFechamodificacion(LocalDateTime.now());
    
        Genero generoGuardada = generoRepository.save(generoExistente);
        return ResponseEntity.ok(generoGuardada);
    }
}