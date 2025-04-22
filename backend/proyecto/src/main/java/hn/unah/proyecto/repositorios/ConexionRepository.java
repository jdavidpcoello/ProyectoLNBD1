
package hn.unah.proyecto.repositorios;
import hn.unah.proyecto.entidades.Conexiones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConexionRepository extends JpaRepository<Conexiones,Integer> {

    @Query("SELECT c FROM Conexiones c WHERE c.usuario1.codigoUsuario = :codigoUsuario1 ")
    List<Conexiones> findByUsuario(int usuario1);

    @Query("SELECT c FROM Conexiones c WHERE (c.usuario1.codigoUsuario = :codigoUsuario1 OR c.usuario2.codigoUsuario = :codigoUsuario2) AND c.estado.codigoEstado = :estado")
    List<Conexiones> findByUsuario1OrUsuario2AndEstado(
        @Param("codigoUsuario1") int usuario1,
        @Param("codigoUsuario2") int usuario2,
        @Param("estado") int estado
    );

    @Query("SELECT c FROM Conexiones c WHERE c.usuario1.codigoUsuario = :codigoUsuario OR c.usuario2.codigoUsuario = :codigoUsuario")
    List<Conexiones> findConexionesPorUsuario1(@Param("codigoUsuario") int codigoUsuario);


    List<Conexiones> findByUsuario2CodigoUsuarioAndEstadoCodigoEstado(int codigoUsuario, int codigoEstado);

}


