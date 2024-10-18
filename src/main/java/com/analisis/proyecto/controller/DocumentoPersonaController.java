package com.analisis.proyecto.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

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

import com.analisis.proyecto.entity.DocumentoPersona;
import com.analisis.proyecto.entity.DocumentoPersonaId;
import com.analisis.proyecto.repository.DocumentoPersonaRepository;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/documentopersona")
@CrossOrigin
public class DocumentoPersonaController {

    @Autowired
    private DocumentoPersonaRepository documentopersonaRepository;


    
    @GetMapping("/buscar")
    public List<DocumentoPersona> getBuscar(@RequestParam(required = false) String param) {
        return documentopersonaRepository.findAll();
    }
    
    
    @GetMapping("/buscar/{idpersona}")
    public ResponseEntity<List<DocumentoPersona>> getByIdPersona(@PathVariable("idpersona") int idpersona) {
        List<DocumentoPersona> documentos = documentopersonaRepository.findByidpersona(idpersona);
        if (documentos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(documentos);
    }

     @PostMapping("/guardar")
    public DocumentoPersona postGuardar(@RequestBody DocumentoPersona documentopersona) {
        documentopersona.setUsuariocreacion("Administrador");
        documentopersona.setFechacreacion(LocalDateTime.now());
        return documentopersonaRepository.save(documentopersona);
    }
    @GetMapping("/listar")
public List<DocumentoPersona> listarDocumentoPersona() {
    return documentopersonaRepository.findAll();
}

 @DeleteMapping("/eliminar/{idtipodocumento}/{idpersona}")
    public ResponseEntity<Void> eliminar(@PathVariable("idtipodocumento") int idtipodocumento, 
                                         @PathVariable("idpersona") int idpersona) {
        // Crear una instancia de DocumentoPersonaId con los valores de idtipodocumento e idpersona
        DocumentoPersonaId documentoPersonaId = new DocumentoPersonaId();
        documentoPersonaId.setIdtipodocumento(idtipodocumento);
        documentoPersonaId.setIdpersona(idpersona);

        // Eliminar la entidad con la clave compuesta
        documentopersonaRepository.deleteById(documentoPersonaId);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/buscarconnombre")
    public List<Map<String, Object>> getbuscarnombre() {
        return documentopersonaRepository.buscarnombre();  // Llamar al método del repositorio
    }


    @PutMapping("/actualizar/{idtipodocumento}/{idpersona}")
    public ResponseEntity<DocumentoPersona> actualizarDocumentoPersona(
            @PathVariable("idtipodocumento") int idtipodocumento, 
            @PathVariable("idpersona") int idpersona, 
            @RequestBody DocumentoPersona documentopersonaActualizada) {
        
        // Crear el ID compuesto para buscar el documento existente
        DocumentoPersonaId documentoPersonaId = new DocumentoPersonaId();
        documentoPersonaId.setIdtipodocumento(idtipodocumento);
        documentoPersonaId.setIdpersona(idpersona);
    
        // Buscar el documento existente
        DocumentoPersona documentopersonaExistente = documentopersonaRepository.findById(documentoPersonaId)
                .orElseThrow(() -> new EntityNotFoundException("Documento no encontrado con idtipodocumento: " + idtipodocumento + " y idpersona: " + idpersona));
    
        // Actualizar los campos necesarios
        documentopersonaExistente.setNodocumento(documentopersonaActualizada.getNodocumento());
        
        // Esta parte no se cambia según tu requerimiento
        documentopersonaExistente.setUsuariomodificacion("Administrador"); // Puedes cambiarlo por el usuario actual
        documentopersonaExistente.setFechamodificacion(LocalDateTime.now());
    
        // Guardar los cambios
        DocumentoPersona documentopersonaGuardada = documentopersonaRepository.save(documentopersonaExistente);
        return ResponseEntity.ok(documentopersonaGuardada);
    }
    
    
}
