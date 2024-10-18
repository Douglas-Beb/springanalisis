package com.analisis.proyecto.entity;

import java.io.Serializable;
import java.util.Objects;

public class DocumentoPersonaId implements Serializable {
    private int idtipodocumento;
    private int idpersona;

    // Getters y setters
    public int getIdtipodocumento() {
        return idtipodocumento;
    }

    public void setIdtipodocumento(int idtipodocumento) {
        this.idtipodocumento = idtipodocumento;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    // hashCode y equals
    @Override
    public int hashCode() {
        return Objects.hash(idtipodocumento, idpersona);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DocumentoPersonaId that = (DocumentoPersonaId) obj;
        return idtipodocumento == that.idtipodocumento &&
               idpersona == that.idpersona;
    }
}
