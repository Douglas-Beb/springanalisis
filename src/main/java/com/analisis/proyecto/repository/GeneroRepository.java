package com.analisis.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.analisis.proyecto.entity.Genero;

@Repository("generoRepository")
public interface GeneroRepository extends JpaRepository<Genero, Integer> {
    List<Genero> findByidgenero(int idgenero);

}