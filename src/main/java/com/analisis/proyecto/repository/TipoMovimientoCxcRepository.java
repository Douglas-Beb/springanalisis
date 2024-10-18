package com.analisis.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.analisis.proyecto.entity.TipoMovimientoCxc;

@Repository("tipomovimientocxcRepository")
public interface  TipoMovimientoCxcRepository extends JpaRepository<TipoMovimientoCxc, Integer> {
    
    List<TipoMovimientoCxc> findByidtipomovimientocxc(int idtipomovimientocxc);
}
