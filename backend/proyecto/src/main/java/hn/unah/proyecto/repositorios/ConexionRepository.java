
package hn.unah.proyecto.repositorios;
import hn.unah.proyecto.entidades.Conexiones;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ConexionRepository extends JpaRepository<Conexiones,Integer> {

    List<Conexiones> findByUsuario1Id(int codigoUsuario1);

    @Query("SELECT c FROM Conexiones c WHERE (c.usuario1Id = :usuario1Id OR c.usuario2Id = :usuario2Id) AND c.estado.codigoEstado = :estado")
    List<Conexiones> findByUsuario1IdOrUsuario2IdAndEstado(
        @Param("usuario1Id") int usuario1Id,
        @Param("usuario2Id") int usuario2Id,
        @Param("estado") int estado
    );

    @Query("SELECT c FROM Conexiones c WHERE (c.usuario1Id = :codigoUsuario OR c.usuario2Id = :codigoUsuario) AND c.estado = :estado")
    List<Conexiones> findConexionesActivasPorUsuarioYEstado(@Param("codigoUsuario") int codigoUsuario, @Param("estado") int estado);

    @Query("SELECT c FROM Conexiones c WHERE c.usuario1Id = :codigoUsuario OR c.usuario2Id = :codigoUsuario")
    List<Conexiones> findConexionesPorUsuario(@Param("codigoUsuario") int codigoUsuario);
}


