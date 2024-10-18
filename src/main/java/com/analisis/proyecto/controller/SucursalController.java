package com.analisis.proyecto.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analisis.proyecto.entity.Sucursal;
import com.analisis.proyecto.repository.SucursalRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/sucursal")
@CrossOrigin
public class SucursalController {
        
    @Autowired
    SucursalRepository sucursalRepository;

    @GetMapping("/buscar")
    public List<Sucursal> getBuscar(@RequestParam(required = false) String param) {
        return sucursalRepository.findAll();
    }


    @GetMapping("/buscar/{idsucursal}")
    public ResponseEntity<Sucursal> getSucursalPorId(@PathVariable("idsucursal") int idsucursal) {
        Sucursal sucursal = sucursalRepository.findById(idsucursal)
                .orElseThrow(() -> new EntityNotFoundException("Sucursal no encontrada con id: " + idsucursal));
        return ResponseEntity.ok(sucursal);
    }

    @DeleteMapping("/eliminar/{idsucursal}")
    public void eliminar(@PathVariable("idsucursal") int idsucursal) {
        sucursalRepository.deleteById(idsucursal);
    }

    @PostMapping("/guardar")
    public Sucursal postGuardar(@RequestBody Sucursal sucursal) {
        sucursal.setUsuariocreacion("Administrador");
        sucursal.setFechacreacion(LocalDateTime.now());
        return sucursalRepository.save(sucursal);
    }

    @PutMapping("/actualizar/{idsucursal}")
public ResponseEntity<Sucursal> actualizarSucursal(@PathVariable("idsucursal") int idsucursal, @RequestBody Sucursal sucursalActualizada) {
    Sucursal sucursalExistente = sucursalRepository.findById(idsucursal)
            .orElseThrow(() -> new EntityNotFoundException("Sucursal no encontrada con id: " + idsucursal));


    sucursalExistente.setNombre(sucursalActualizada.getNombre());
    sucursalExistente.setDireccion(sucursalActualizada.getDireccion());
    sucursalExistente.setUsuariomodificacion("Administrador"); // Puedes cambiarlo por el usuario actual
    sucursalExistente.setFechamodificacion(LocalDateTime.now());

    Sucursal sucursalGuardada = sucursalRepository.save(sucursalExistente);
    return ResponseEntity.ok(sucursalGuardada);
}

@GetMapping("/listar")
public List<Sucursal> listarSucursal() {
    return sucursalRepository.findAll();
}

}
