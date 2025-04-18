package hn.unah.proyecto.repositorios;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;


import hn.unah.proyecto.entidades.Mensajes;


public interface MensajesRepository extends JpaRepository<Mensajes, Integer>{
     
    @Query("SELECT c FROM Mensajes c WHERE codigoMensaje = :codigo")
    public Mensajes findByCodigoMensaje(@Param("codigo") int codigo); 
}
