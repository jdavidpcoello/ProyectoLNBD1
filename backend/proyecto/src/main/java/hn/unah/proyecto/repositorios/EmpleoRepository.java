package hn.unah.proyecto.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hn.unah.proyecto.entidades.Empleos;

@Repository
public interface EmpleoRepository extends JpaRepository<Empleos, Integer>{
    
    @Query("SELECT e FROM Empleos e WHERE e.codigoEmpleo = :codigoEmpleo")
    public Empleos findByCodigoEmpleo(int codigoEmpleo); 

    @Query("SELECT e FROM Empleos e WHERE e.codigoPublicacion.usuario.codigoUsuario <> :codigoUsuario")
    List<Empleos> findAllExceptByUsuario(@Param("codigoUsuario") int codigoUsuario);

}

