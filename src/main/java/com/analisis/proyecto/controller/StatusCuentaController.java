package com.analisis.proyecto.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.analisis.proyecto.entity.StatusCuenta;
import com.analisis.proyecto.repository.StatusCuentaRepository;

import jakarta.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/statuscuenta")
@CrossOrigin

public class StatusCuentaController {
    @Autowired
    StatusCuentaRepository statuscuentaRepository;

    @GetMapping("/buscar")
    public List<StatusCuenta> getBuscar(@RequestParam(required = false) String param) {
        return statuscuentaRepository.findAll();
    }

    @PostMapping("/guardar")
    public StatusCuenta postGuardar(@RequestBody StatusCuenta statuscuenta) {
        statuscuenta.setUsuariocreacion("Administrador");
        statuscuenta.setFechacreacion(LocalDateTime.now());
        return statuscuentaRepository.save(statuscuenta);
    }


    @PutMapping("/actualizar/{idstatuscuenta}")
    public ResponseEntity<StatusCuenta> actualizarStatusCuenta(@PathVariable("idstatuscuenta") Integer idstatuscuenta, @RequestBody StatusCuenta statuscuentaActualizado) {
    StatusCuenta statuscuentaExistente = statuscuentaRepository.findById(idstatuscuenta)
            .orElseThrow(() -> new EntityNotFoundException("Status cuenta no encontrado con id: " + idstatuscuenta));

    statuscuentaExistente.setNombre(statuscuentaActualizado.getNombre());
    statuscuentaExistente.setUsuariomodificacion("Administrador");
    statuscuentaExistente.setFechamodificacion(LocalDateTime.now());

    StatusCuenta statuscuentaGuardada = statuscuentaRepository.save(statuscuentaExistente);
    return ResponseEntity.ok(statuscuentaGuardada);
}

    @DeleteMapping("/eliminar/{idstatuscuenta}")
    public void eliminar(@PathVariable("idstatuscuenta") Integer idstatuscuenta) {
        statuscuentaRepository.deleteById(idstatuscuenta);
    }

}
