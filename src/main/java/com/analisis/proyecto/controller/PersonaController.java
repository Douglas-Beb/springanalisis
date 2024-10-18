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

import com.analisis.proyecto.entity.Persona;
import com.analisis.proyecto.repository.PersonaRepository;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/persona")
@CrossOrigin
public class PersonaController {

    @Autowired
    PersonaRepository personaRepository;

    @GetMapping("/buscar")
    public List<Persona> getBuscar(@RequestParam(required = false) Integer param) {
        return personaRepository.findAll();
    }

    @GetMapping("/buscar/{idpersona}")
    public ResponseEntity<Persona> getPersonaPorId(@PathVariable("idpersona") Integer idpersona) {
        Persona persona = personaRepository.findById(idpersona)
                .orElseThrow(() -> new RuntimeException("persona no encontrado con id: " + idpersona));
        return ResponseEntity.ok(persona);
    }

    @PostMapping("/guardar")
    public Persona postGuardar(@RequestBody Persona persona) {
        persona.setUsuariocreacion("Administrador");
        persona.setFechacreacion(LocalDateTime.now());
        return personaRepository.save(persona);
    }

    @PutMapping("/actualizar/{idpersona}")
public ResponseEntity<Persona> actualizarPersona(@PathVariable("idpersona") Integer idpersona, @RequestBody Persona personaActualizada) {
    Persona personaExistente = personaRepository.findById(idpersona)
            .orElseThrow(() -> new EntityNotFoundException("Persona no encontrada con id: " + idpersona));

    personaExistente.setNombre(personaActualizada.getNombre());
    personaExistente.setApellido(personaActualizada.getApellido());
    personaExistente.setFechanacimiento(personaActualizada.getFechanacimiento());
    personaExistente.setCorreoelectronico(personaActualizada.getCorreoelectronico());
    personaExistente.setIdestadocivil(personaActualizada.getIdestadocivil());
    personaExistente.setDireccion(personaActualizada.getDireccion());
    personaExistente.setTelefono(personaActualizada.getTelefono());
    personaExistente.setUsuariomodificacion("Administrador");
    personaExistente.setFechamodificacion(LocalDateTime.now());

    Persona personaGuardada = personaRepository.save(personaExistente);
    return ResponseEntity.ok(personaGuardada);
}

    @DeleteMapping("/eliminar/{idpersona}")
    public void eliminar(@PathVariable("idpersona") Integer idpersona) {
        personaRepository.deleteById(idpersona);
    }

}