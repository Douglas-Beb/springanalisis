package com.analisis.proyecto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.analisis.proyecto.entity.EstadoCivil;



@Repository("estadocivilRepository")
public interface EstadoCivilRepository extends JpaRepository<EstadoCivil, Integer>  {
     List<EstadoCivil> findByidestadocivil(int idestadocivil);
    
}
