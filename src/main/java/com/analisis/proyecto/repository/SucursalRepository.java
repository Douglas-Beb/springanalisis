package com.analisis.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.analisis.proyecto.entity.Sucursal;


@Repository("sucursalRepository")
public interface SucursalRepository extends JpaRepository<Sucursal, Integer> {
    List<Sucursal> findByidsucursal(int idsucursal);

}