package com.analisis.proyecto.controller;

import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.analisis.proyecto.entity.Usuario;
import com.analisis.proyecto.repository.UsuarioRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/usuario")
@CrossOrigin
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @GetMapping("/buscar")
    public List<Usuario> getBuscar(@RequestParam(required = false) String param) {
        return usuarioRepository.findAll();
    }

    @GetMapping("/buscar/{idusuario}")
    public ResponseEntity<Usuario> getUsuarioPorId(@PathVariable("idusuario") String idusuario) {
        Usuario usuario = usuarioRepository.findById(idusuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + idusuario));
        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/guardar")
    public Usuario postGuardar(@RequestBody Usuario usuario) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encryptedPassword);
        usuario.setUsuariocreacion("system");
        usuario.setFechacreacion(LocalDateTime.now());
        return usuarioRepository.save(usuario);
    }

    @PutMapping("/actualizar/{idusuario}")
    public ResponseEntity<Usuario> actualizarPassword(@PathVariable("idusuario") String idusuario,
            @RequestBody Map<String, String> requestBody) {

        Usuario usuarioExistente = usuarioRepository.findById(idusuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + idusuario));

        String nuevaPassword = requestBody.get("password");

        if (nuevaPassword != null && !nuevaPassword.isEmpty()) {
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String encryptedPassword = passwordEncoder.encode(nuevaPassword);

            usuarioExistente.setPassword(encryptedPassword);
            usuarioExistente.setUltimafechacambiopassword(LocalDateTime.now());

            Usuario usuarioActualizado = usuarioRepository.save(usuarioExistente);
            return ResponseEntity.ok(usuarioActualizado);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/eliminar/{idusuario}")
    public void eliminar(@PathVariable("idusuario") String idusuario) {
        usuarioRepository.deleteById(idusuario);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        List<Usuario> listaUsuarios = usuarioRepository.findByIdusuario(usuario.getIdusuario());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (!listaUsuarios.isEmpty()) {
            Usuario usuarioEncontrado = listaUsuarios.get(0);

            if (usuarioEncontrado.getIdstatususuario() == 2) {
                if (usuarioEncontrado.getUltimafechaingreso() != null &&
                        LocalDateTime.now().isBefore(usuarioEncontrado.getUltimafechaingreso().plusMinutes(1))) {
                    return ResponseEntity.status(HttpStatus.LOCKED)
                            .body("Cuenta bloqueada temporalmente. Intente de nuevo en 1 minuto.");
                } else {
                    usuarioEncontrado.setIdstatususuario(1);
                    usuarioEncontrado.setIntentosdeacceso(0);
                }
            }

            if (passwordEncoder.matches(usuario.getPassword(), usuarioEncontrado.getPassword())) {
                usuarioEncontrado.setUltimafechaingreso(LocalDateTime.now());
                usuarioEncontrado.setIntentosdeacceso(0);
                usuarioEncontrado.setIdstatususuario(1);
                usuarioRepository.save(usuarioEncontrado);
                return ResponseEntity.ok(usuarioEncontrado);
            } else {
                int intentos = usuarioEncontrado.getIntentosdeacceso() + 1;
                usuarioEncontrado.setIntentosdeacceso(intentos);
                if (intentos >= 5) {
                    usuarioEncontrado.setIdstatususuario(2);
                    usuarioEncontrado.setUltimafechaingreso(LocalDateTime.now());
                }
                usuarioRepository.save(usuarioEncontrado);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos.");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario o contraseña incorrectos.");
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestBody Usuario usuario) {
        List<Usuario> listaUsuarios = usuarioRepository.findByIdusuario(usuario.getIdusuario());

        if (!listaUsuarios.isEmpty()) {
            Usuario usuarioEncontrado = listaUsuarios.get(0);
            usuarioEncontrado.setIdstatususuario(3);
            usuarioRepository.save(usuarioEncontrado);
            return ResponseEntity.ok("Sesión cerrada correctamente.");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no encontrado.");
    }

    @PutMapping("/actualizarestatus")
    public ResponseEntity<Usuario> actualizarEstatusUsuario(@RequestBody Map<String, Object> requestBody) {
        String idUsuario = (String) requestBody.get("idusuario");
        Integer nuevoEstatus = (Integer) requestBody.get("estatus");
    
        Usuario usuarioExistente = usuarioRepository.findById(idUsuario)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + idUsuario));
    
        usuarioExistente.setIdstatususuario(nuevoEstatus);
        usuarioExistente.setUsuariomodificacion("Administrador");
        usuarioExistente.setFechamodificacion(LocalDateTime.now());
    
        Usuario usuarioActualizado = usuarioRepository.save(usuarioExistente);
    
        return ResponseEntity.ok(usuarioActualizado);
    }

    @PutMapping("/actualizarPerfil/{idusuario}")
    public ResponseEntity<Usuario> actualizarPerfil(@PathVariable("idusuario") String idusuario,
            @RequestBody Map<String, String> requestBody) {
    
        Usuario usuarioExistente = usuarioRepository.findById(idusuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + idusuario));
    
        // Actualizar los datos con los nuevos valores del requestBody
        usuarioExistente.setNombre(requestBody.get("nombre"));
        usuarioExistente.setApellido(requestBody.get("apellido"));
        usuarioExistente.setCorreoelectronico(requestBody.get("correoelectronico"));
        usuarioExistente.setFechamodificacion((LocalDateTime.now()));
        usuarioExistente.setUsuariomodificacion("system");
        usuarioExistente.setTelefonomovil(requestBody.get("telefonomovil"));
    
        // Convertir la fecha de nacimiento desde la cadena
        try {
            LocalDate fechaNacimiento = LocalDate.parse(requestBody.get("fechanacimiento"));
            usuarioExistente.setFechanacimiento(fechaNacimiento);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Formato de fecha inválido: " + requestBody.get("fechanacimiento"));
        }
    
        Usuario usuarioActualizado = usuarioRepository.save(usuarioExistente);
        return ResponseEntity.ok(usuarioActualizado);
    }

}