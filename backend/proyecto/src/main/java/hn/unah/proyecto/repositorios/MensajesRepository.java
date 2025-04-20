package hn.unah.proyecto.repositorios;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import hn.unah.proyecto.dto.MensajeDTO;
import hn.unah.proyecto.entidades.Mensajes;

@Repository
public interface MensajesRepository extends JpaRepository<Mensajes, Integer>{
     
    public Mensajes findByCodigoMensaje(int codigoMensaje);
    
    
}
