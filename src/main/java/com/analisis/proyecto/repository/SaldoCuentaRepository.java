package com.analisis.proyecto.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.analisis.proyecto.entity.SaldoCuenta;


@Repository("saldoCuentaRepository")
public interface SaldoCuentaRepository extends JpaRepository<SaldoCuenta, Integer> {
    List<SaldoCuenta> findByidsaldocuenta(int idsaldocuenta);
    

        @Query(value = "SELECT sc.*, p.nombre AS nombrePersona, stc.nombre AS statusCuenta, tsc.nombre AS tipoSaldoCuenta "
             + "FROM saldo_cuenta sc "
             + "INNER JOIN persona p ON sc.idpersona = p.idpersona "
             + "INNER JOIN status_cuenta stc ON sc.idstatuscuenta = stc.idstatuscuenta "
             + "INNER JOIN tipo_saldo_cuenta tsc ON sc.idtiposaldocuenta = tsc.idtiposaldocuenta", 
             nativeQuery = true)
    List<Map<String, Object>> buscarTodosLosSaldosConDetalles();
}