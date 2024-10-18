package com.analisis.proyecto.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "movimiento_cuenta")
public class MovimientoCuenta implements Serializable{
    
    @Id
    @Column(name = "idmovimientocuenta")
    private int idmovimientocuenta;

    @Column(name = "idsaldocuenta")
    private int idsaldocuenta;

    @Column(name = "idmovimientocxc")
    private int idmovimientocxc;

    @Column(name = "fechamovimiento")
    private LocalDateTime fechamovimiento;

    @Column(name = "valormovimiento")
    private double valormovimiento;

    @Column(name = "valormovimientopagado")
    private double valormovimientopagado;

    @Column(name = "generadoautomaticamente")
    private boolean generadoautomaticamente;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fechacreacion")
    private LocalDateTime fechacreacion;

    @Column(name = "usuariocreacion")
    private String usuariocreacion;

    @Column(name = "fechamodificacion")
    private LocalDateTime fechamodificacion;

    @Column(name = "usuariomodificacion")
    private String usuariomodificacion;
}
