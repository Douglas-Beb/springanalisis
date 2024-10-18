package com.analisis.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.analisis.proyecto.entity.StatusCuenta;

@Repository("statuscuentaRepository")
public interface StatusCuentaRepository extends JpaRepository<StatusCuenta, Integer> {
    List<StatusCuenta> findByidstatuscuenta(int idstatuscuenta);

}