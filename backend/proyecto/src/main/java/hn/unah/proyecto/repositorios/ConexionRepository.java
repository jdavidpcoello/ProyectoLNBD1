
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

    List<Conexiones> findByUsuario1IdOrUsuario2IdAndEstado(int usuario1Id, int usuario2Id, int estado);

    @Query("SELECT c FROM Conexiones c WHERE (c.usuario1Id = :codigoUsuario OR c.usuario2Id = :codigoUsuario) AND c.estado = :estado")
    List<Conexiones> findConexionesActivasPorUsuarioYEstado(@Param("codigoUsuario") int codigoUsuario, @Param("estado") int estado);

}


