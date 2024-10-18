package com.analisis.proyecto.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analisis.proyecto.entity.UsuarioPregunta;
import com.analisis.proyecto.repository.UsuarioPreguntaRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/usuarioPregunta")
@CrossOrigin
public class UsuarioPreguntaController {

    @Autowired
    UsuarioPreguntaRepository usuarioPreguntaRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); // Instancia de BCrypt

    @GetMapping("/buscar/{idusuario}")
    public ResponseEntity<List<UsuarioPregunta>> getBuscarPorIdUsuario(@PathVariable String idusuario) {
        List<UsuarioPregunta> resultados;
        try {
            int id = Integer.parseInt(idusuario);
            resultados = usuarioPreguntaRepository.findByIdpregunta(id);
            if (resultados.isEmpty()) {
                resultados = usuarioPreguntaRepository.findByIdusuario(idusuario);
            }
        } catch (NumberFormatException e) {
            resultados = usuarioPreguntaRepository.findByIdusuario(idusuario);
        }
        return ResponseEntity.ok(resultados);
    }

    @PostMapping("/guardar")
    public ResponseEntity<UsuarioPregunta> postGuardar(@Validated @RequestBody UsuarioPregunta usuarioPregunta) {
        // Encriptar la respuesta antes de guardar
        String encryptedRespuesta = passwordEncoder.encode(usuarioPregunta.getRespuesta());
        usuarioPregunta.setRespuesta(encryptedRespuesta);
        usuarioPregunta.setFechacreacion(LocalDateTime.now());
        usuarioPregunta.setUsuariocreacion("system");
        
        UsuarioPregunta savedUsuario = usuarioPreguntaRepository.save(usuarioPregunta);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuario);
    }

    @PostMapping("/verificarRespuesta")
    public ResponseEntity<Map<String, String>> verificarRespuesta(@RequestBody UsuarioPregunta usuarioPregunta) {
        Map<String, String> response = new HashMap<>();

        List<UsuarioPregunta> preguntas = usuarioPreguntaRepository.findByIdusuario(usuarioPregunta.getIdusuario());

        if (preguntas.isEmpty()) {
            response.put("message", "Usuario no encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Buscar la pregunta de seguridad
        UsuarioPregunta pregunta = preguntas.stream()
                .filter(p -> p.getPregunta().equals(usuarioPregunta.getPregunta()))
                .findFirst()
                .orElse(null);

        if (pregunta == null) {
            response.put("message", "Pregunta de seguridad no encontrada.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        // Verificar si la respuesta ingresada coincide con la respuesta encriptada
        if (passwordEncoder.matches(usuarioPregunta.getRespuesta(), pregunta.getRespuesta())) {
            response.put("message", "Respuesta correcta.");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Respuesta incorrecta.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @PutMapping("/actualizar/{idusuario}")
    public ResponseEntity<UsuarioPregunta> putActualizar(@PathVariable("idusuario") String idusuario, @RequestBody UsuarioPregunta usuarioPregunta) {
        UsuarioPregunta usuarioExistente = usuarioPreguntaRepository.findByIdusuario(idusuario)
                .stream().findFirst().orElse(null);

        if (usuarioExistente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        // Encriptar la nueva respuesta antes de actualizar
        String encryptedRespuesta = passwordEncoder.encode(usuarioPregunta.getRespuesta());
        usuarioExistente.setPregunta(usuarioPregunta.getPregunta());
        usuarioExistente.setRespuesta(encryptedRespuesta);
        
        UsuarioPregunta usuarioActualizado = usuarioPreguntaRepository.save(usuarioExistente);
        return ResponseEntity.ok(usuarioActualizado);
    }

}
