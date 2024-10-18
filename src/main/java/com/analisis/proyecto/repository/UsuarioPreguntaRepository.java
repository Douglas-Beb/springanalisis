package com.analisis.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.analisis.proyecto.entity.UsuarioPregunta;

@Repository("usuarioPreguntaRepository")
public interface UsuarioPreguntaRepository extends JpaRepository<UsuarioPregunta, Integer> {
    public List<UsuarioPregunta> findByIdpregunta(int idpregunta);
    public List<UsuarioPregunta> findByIdusuario(String idusuario);

}
