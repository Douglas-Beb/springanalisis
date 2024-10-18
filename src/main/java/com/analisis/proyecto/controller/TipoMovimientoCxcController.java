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

import com.analisis.proyecto.entity.TipoMovimientoCxc;
import com.analisis.proyecto.repository.TipoMovimientoCxcRepository;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/tipomovimientocxc")
@CrossOrigin
public class TipoMovimientoCxcController {
    @Autowired
    TipoMovimientoCxcRepository tipomovimientocxcRepository;


    @GetMapping("/buscar")
    public List<TipoMovimientoCxc> getBuscar(@RequestParam(required = false) String param) {
        return tipomovimientocxcRepository.findAll();
    }

    @PostMapping("/guardar")
    public TipoMovimientoCxc postGuardar(@RequestBody TipoMovimientoCxc tipomovimientocxc) {
        tipomovimientocxc.setUsuariocreacion("Administrador");
        tipomovimientocxc.setFechacreacion(LocalDateTime.now());
        return tipomovimientocxcRepository.save(tipomovimientocxc);
    }

    @PutMapping("/actualizar/{idtipomovimientocxc}")
    public ResponseEntity<TipoMovimientoCxc> actualizarTipoMovimientoCxc(@PathVariable("idtipomovimientocxc") Integer idtipomovimientocxc, @RequestBody TipoMovimientoCxc tipomovimientocxcActualizado) {
        TipoMovimientoCxc tipomovimientocxcExistente = tipomovimientocxcRepository.findById(idtipomovimientocxc)
            .orElseThrow(() -> new EntityNotFoundException("Tipo movimiento cxc no encontrado con id: " + idtipomovimientocxc));

            tipomovimientocxcExistente.setNombre(tipomovimientocxcActualizado.getNombre());
            tipomovimientocxcExistente.setOperacioncuentacorriente(tipomovimientocxcActualizado.getOperacioncuentacorriente());
            tipomovimientocxcExistente.setUsuariomodificacion("Administrador");
            tipomovimientocxcExistente.setFechamodificacion(LocalDateTime.now());

    TipoMovimientoCxc tipomovimientocxcGuardada = tipomovimientocxcRepository.save(tipomovimientocxcExistente);
    return ResponseEntity.ok(tipomovimientocxcGuardada);
}

    @DeleteMapping("/eliminar/{idtipomovimientocxc}")
    public void eliminar(@PathVariable("idtipomovimientocxc") Integer idtipomovimientocxc) {
        tipomovimientocxcRepository.deleteById(idtipomovimientocxc);
    }

}
