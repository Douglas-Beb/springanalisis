package com.analisis.proyecto.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "documentoPersona")
@IdClass(DocumentoPersonaId.class) 
public class DocumentoPersona implements Serializable {

    @Id
    @Column(name = "idtipodocumento")
    private int idtipodocumento;

    @Id
    @Column(name = "idpersona")
    private int idpersona;

    @Column(name = "nodocumento")
    private String nodocumento;

    @Column(name = "fechacreacion")
    private LocalDateTime fechacreacion;

    @Column(name = "usuariocreacion")
    private String usuariocreacion;

    @Column(name = "fechamodificacion")
    private LocalDateTime fechamodificacion;

    @Column(name = "usuariomodificacion")
    private String usuariomodificacion;
}
