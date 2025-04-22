package hn.unah.proyecto.repositorios;


import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.unah.proyecto.entidades.Mensajes;

@Repository
public interface MensajesRepository extends JpaRepository<Mensajes, Integer>{
    
    @Query("SELECT m FROM Mensajes m WHERE m.codigoChat = :codigoChat")
    public Mensajes findByCodigoMensaje(int codigoMensaje);
    
    @Query("SELECT m FROM Mensajes m where m.codigoChat = :codigoChat")
    public List<Mensajes> findByCodigoChat(int codigoChat);

    // @Query("select max(fecha_mensaje) from Mensajes.m")
    // public Mensajes findUltimoMensaje(int codigoChat);
}
