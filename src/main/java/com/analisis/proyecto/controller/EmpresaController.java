package com.analisis.proyecto.controller;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analisis.proyecto.entity.Empresa;
import com.analisis.proyecto.repository.EmpresaRepository;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/empresa")
@CrossOrigin
public class EmpresaController {
    
    @Autowired
    EmpresaRepository empresaRepository;

    @GetMapping("/buscar")
    public List<Empresa> getBuscar(@RequestParam(required = false) String param) {
        return empresaRepository.findAll();
    }


    @GetMapping("/buscar/{idempresa}")
    public ResponseEntity<Empresa> getEmpresaPorId(@PathVariable("idempresa") int idempresa) {
        Empresa empresa = empresaRepository.findById(idempresa)
                // .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + idempresa));
                .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada con id: " + idempresa));
        return ResponseEntity.ok(empresa);
    }

    @DeleteMapping("/eliminar/{idempresa}")
    public void eliminar(@PathVariable("idempresa") int idempresa) {
        empresaRepository.deleteById(idempresa);
    }

    @PostMapping("/guardar")
    public Empresa postGuardar(@RequestBody Empresa empresa) {
        empresa.setUsuariocreacion("Administrador");
        empresa.setFechacreacion(LocalDateTime.now());
        return empresaRepository.save(empresa);
    }

    @PutMapping("/actualizar/{idempresa}")
public ResponseEntity<Empresa> actualizarEmpresa(@PathVariable("idempresa") int idempresa, @RequestBody Empresa empresaActualizada) {
    Empresa empresaExistente = empresaRepository.findById(idempresa)
            .orElseThrow(() -> new EntityNotFoundException("Empresa no encontrada con id: " + idempresa));

    // Actualizar los campos necesarios
    empresaExistente.setNit(empresaActualizada.getNit());
    empresaExistente.setDireccion(empresaActualizada.getDireccion());
    empresaExistente.setUsuariomodificacion("Administrador"); // Puedes cambiarlo por el usuario actual
    empresaExistente.setFechamodificacion(LocalDateTime.now());

    Empresa empresaGuardada = empresaRepository.save(empresaExistente);
    return ResponseEntity.ok(empresaGuardada);
}

@GetMapping("/listar")
public List<Empresa> listarEmpresas() {
    return empresaRepository.findAll();
}

}
