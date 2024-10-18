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
@Table(name = "sucursal")
public class Sucursal implements Serializable{
    
    @Id
    @Column(name = "idsucursal")
    private int idsucursal;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;  

    @Column(name = "idempresa")
    private int idempresa;

    @Column(name = "fechacreacion")
    private LocalDateTime fechacreacion;  

    @Column(name = "usuariocreacion")
    private String usuariocreacion;

    @Column(name = "fechamodificacion")
    private LocalDateTime fechamodificacion;  

    @Column(name = "usuariomodificacion")
    private String usuariomodificacion;  

}
