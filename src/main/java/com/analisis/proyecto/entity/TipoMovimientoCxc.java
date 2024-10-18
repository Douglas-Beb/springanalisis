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
@Table(name = "tipoMovimientoCxc")
public class TipoMovimientoCxc implements Serializable{

    @Id
    @Column(name = "idtipomovimientocxc")
    private int idtipomovimientocxc;
    
    @Column(name = "nombre")
    private String nombre;

    @Column(name = "operacioncuentacorriente")
    private int operacioncuentacorriente;

    @Column(name = "fechacreacion")
    private LocalDateTime fechacreacion;

    @Column(name = "usuariocreacion")
    private String usuariocreacion;

    @Column(name = "fechamodificacion")
    private LocalDateTime fechamodificacion;

    @Column(name = "usuariomodificacion")
    private String usuariomodificacion;
}
