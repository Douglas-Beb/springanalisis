package com.analisis.proyecto.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.analisis.proyecto.entity.DocumentoPersona;
import com.analisis.proyecto.entity.DocumentoPersonaId;

@Repository
public interface DocumentoPersonaRepository extends JpaRepository<DocumentoPersona,DocumentoPersonaId> {
    List<DocumentoPersona> findByidtipodocumento(int idtipodocumento);
    List<DocumentoPersona> findByidpersona(int idpersona);

 
@Query(value = "Select dp.*,tp.nombre as Tipo_Documento, p.nombre as nombre\n"
        + "from documento_persona dp\n" 
        +"inner join tipo_documento tp\n" 
        +"on dp.IdTipoDocumento = tp.IdTipoDocumento\n" 
        +"inner join persona p\n"
        +"on dp.IdPersona = p.IdPersona;", 
             nativeQuery = true)
    List<Map<String, Object>> buscarnombre();

}
