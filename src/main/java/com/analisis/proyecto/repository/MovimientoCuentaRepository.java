package com.analisis.proyecto.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.analisis.proyecto.entity.MovimientoCuenta;


@Repository("movimientoCuentaRepository")
public interface MovimientoCuentaRepository extends JpaRepository<MovimientoCuenta, Integer> {
    List<MovimientoCuenta> findByidmovimientocuenta(int idmovimientocuenta);


       
@Query(value = "  select  perS.nombre as nombre_persona ,movC.IdMovimientoCuenta as ID, tipMC.nombre as tipo_movimiento, movC.Descripcion as descripcion,\n" + //
        " movC.FechaMovimiento as Fecha_movimiento, movC.ValorMovimiento as Valor_Movimiento\n" + //
        "  from movimiento_cuenta movC\n" + //
        "inner join saldo_cuenta saldC\n" + //
        "on movC.IdSaldoCuenta = saldC.IdSaldoCuenta\n" + //
        "inner join tipo_movimiento_cxc tipMC\n" + //
        "on tipMC.IdTipoMovimientoCXC = movC.IdTipoMovimientoCXC \n" + //
        "inner join persona perS\n" + //
        "on saldC.IdPersona = perS.IdPersona;", 
     nativeQuery = true)
List<Map<String, Object>> buscarnombre();

}


