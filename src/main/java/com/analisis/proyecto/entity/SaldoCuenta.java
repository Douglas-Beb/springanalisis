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
@Table(name = "saldo_cuenta")
public class SaldoCuenta implements Serializable {

    @Id
    @Column(name = "idsaldocuenta")
    private int idsaldocuenta;

    @Column(name = "idpersona")
    private int idpersona;

    @Column(name = "idstatuscuenta")
    private int idstatuscuenta;

    @Column(name = "idtiposaldocuenta")
    private int idtiposaldocuenta;

    @Column(name = "saldoanterior")
    private double saldoanterior;

    @Column(name = "debitos")
    private double debitos;

    @Column(name = "creditos")
    private double creditos;

    @Column(name = "fechacreacion")
    private LocalDateTime fechacreacion;

    @Column(name = "usuariocreacion")
    private String usuariocreacion;

    @Column(name = "fechamodificacion")
    private LocalDateTime fechamodificacion;

    @Column(name = "usuariomodificacion")
    private String usuariomodificacion;

    @Column(name = "saldoactual")
    private double saldoactual;
    
}
