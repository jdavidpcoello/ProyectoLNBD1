/*package hn.unah.proyecto.repositorios;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hn.unah.proyecto.entidades.UsuariosChats;



@Repository
public interface UsuarioChatsRepository extends JpaRepository<UsuariosChats, Integer> {
    
    @Query("SELECT e FROM UsuariosChats e WHERE e.codigoChat = :codigoChat")
    public List<UsuariosChats> findByCodigoChat(@Param("codigoChat") int codigoChat);
}
 */