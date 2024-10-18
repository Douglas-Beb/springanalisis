package com.analisis.proyecto.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.analisis.proyecto.entity.MovimientoCuenta;
import com.analisis.proyecto.repository.MovimientoCuentaRepository;

import jakarta.persistence.EntityNotFoundException;



@RestController
@RequestMapping("/movimientoCuenta")
@CrossOrigin
public class MovimientoCuentaController {
    @Autowired
    MovimientoCuentaRepository movimientoCuentaRepository;

     @GetMapping("/buscar")
    public List<MovimientoCuenta> getBuscar(@RequestParam(required = false) String param) {
        return movimientoCuentaRepository.findAll();
    }

    @GetMapping("/buscar/{idmovimientocuenta}")
    public ResponseEntity<MovimientoCuenta> getSaldoCuentaPorId(@PathVariable("idmovimientocuenta") int idmovimientocuenta) {
        MovimientoCuenta movimientoCuenta = movimientoCuentaRepository.findById(idmovimientocuenta)
                .orElseThrow(() -> new EntityNotFoundException("Movimiento no encontrada con id: " + idmovimientocuenta));
        return ResponseEntity.ok(movimientoCuenta);
    }
    

    @GetMapping("/listar")
    public List<MovimientoCuenta> listarMovimientoCuenta() {
    return movimientoCuentaRepository.findAll(); 
    }

     @GetMapping("/buscarconnombre")
    public List<Map<String, Object>> getbuscarnombre() {
        return  movimientoCuentaRepository.buscarnombre();  // Llamar al método del repositorio
    }


    
}

