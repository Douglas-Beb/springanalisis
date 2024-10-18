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
@Table(name = "statusCuenta")
public class StatusCuenta implements Serializable {

    @Id
    @Column(name = "idstatuscuenta")
    private int idstatuscuenta;

    @Column(name = "nombre")
    private String nombre;

       @Column(name = "fechacreacion")
    private LocalDateTime fechacreacion;

    @Column(name = "usuariocreacion")
    private String usuariocreacion;

    @Column(name = "fechamodificacion")
    private LocalDateTime fechamodificacion;

    @Column(name = "usuariomodificacion")
    private String usuariomodificacion;


    
}
