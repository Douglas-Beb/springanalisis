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
@Table(name = "empresa")
public class Empresa implements Serializable {

    @Id
    @Column(name = "idempresa")
    private int idempresa;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "direccion")
    private String direccion;  

    @Column(name = "nit")
    private String nit;

    @Column(name = "passwordcantidadmayusculas")
    private int passwordcantidadmayusculas;

    @Column(name = "passwordcantidadminusculas")
    private int passwordcantidadminusculas;

    @Column(name = "passwordcantidadcaracteresespeciales")
    private int passwordcantidadcaracteresespeciales;

    @Column(name = "passwordcantidadcaducidaddias")
    private int passwordcantidadcaducidaddias;

    @Column(name = "passwordlargo")
    private int passwordlargo;

    @Column(name = "passwordintentosantesdebloquear")
    private int passwordintentosantesdebloquear;

    @Column(name = "passwordcantidadnumeros")
    private int passwordcantidadnumeros;

    @Column(name = "passwordcantidadpreguntasvalidar")
    private int passwordcantidadpreguntasvalidar;

    @Column(name = "fechacreacion")
    private LocalDateTime fechacreacion;

    @Column(name = "usuariocreacion")
    private String usuariocreacion;

    @Column(name = "fechamodificacion")
    private LocalDateTime fechamodificacion;

    @Column(name = "usuariomodificacion")
    private String usuariomodificacion;


}
