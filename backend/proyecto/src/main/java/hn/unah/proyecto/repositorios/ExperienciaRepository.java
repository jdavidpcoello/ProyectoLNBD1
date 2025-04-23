package hn.unah.proyecto.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import hn.unah.proyecto.entidades.Experiencias;

public interface ExperienciaRepository extends JpaRepository<Experiencias, Integer>{
    
     @Query("SELECT e FROM Experiencias e WHERE e.usuario.codigoUsuario = :codigoUsuario ORDER BY e.anioFinal DESC")
    List<Experiencias> findUltimaExperienciaPorUsuario(@Param("codigoUsuario") int codigoUsuario);

    public List<Experiencias> findAllByUsuarioCodigoUsuario(int codigoUsuario);

}
