package com.analisis.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.analisis.proyecto.entity.Empresa;


@Repository("empresaRepository")
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {
    List<Empresa> findByidempresa(int idempresa);

}
