package com.analisis.proyecto.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @Column(name = "idusuario")
    private String idusuario;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "fechanacimiento")
    private LocalDate fechanacimiento;

    @Column(name = "idstatususuario")
    private int idstatususuario;

    @Column(name = "password")
    private String password;

    @Column(name = "idgenero")
    private int idgenero;

    @Column(name = "ultimafechaingreso")
    private LocalDateTime ultimafechaingreso;

    @Column(name = "intentosdeacceso")
    private int intentosdeacceso;

    @Column(name = "sesionactual")
    private String sesionactual;

    @Column(name = "ultimafechacambiopassword")
    private LocalDateTime ultimafechacambiopassword;

    @Column(name = "correoelectronico")
    private String correoelectronico;

    @Column(name = "requierecambiarpassword")
    private int requierecambiarpassword;

    @Column(name = "fotografia", columnDefinition = "LONGTEXT")
    private String fotografia;

    @Column(name = "telefonomovil")
    private String telefonomovil;

    @Column(name = "idsucursal")
    private int idsucursal;

    @Column(name = "fechacreacion")
    private LocalDateTime fechacreacion;

    @Column(name = "usuariocreacion")
    private String usuariocreacion;

    @Column(name = "fechamodificacion")
    private LocalDateTime fechamodificacion;

    @Column(name = "usuariomodificacion")
    private String usuariomodificacion;

}
