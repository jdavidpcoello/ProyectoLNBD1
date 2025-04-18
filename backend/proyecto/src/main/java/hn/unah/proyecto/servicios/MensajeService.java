package hn.unah.proyecto.servicios;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hn.unah.proyecto.dto.MensajeDTO;
import hn.unah.proyecto.entidades.Mensajes;
import hn.unah.proyecto.repositorios.MensajesRepository;

@Service
public class MensajeService {

    @Autowired
    private MensajesRepository mensajesRepository;

    public MensajeDTO obtenerMensajeDTO(int codigo) {
        Mensajes mensaje = null;
        try {
            mensaje = mensajesRepository.findByCodigoMensaje(codigo);
        } catch (Exception e) {
            System.err.println("Error al buscar el mensaje: " + e.getMessage());
            e.printStackTrace();
        }

        if (mensaje == null) {
            System.out.println("No se encontró el mensaje");
            return null;
        }
    
        System.out.println("Mensaje encontrado: " + mensaje.getMensaje());

        return new MensajeDTO(
            mensaje.getMensaje(),
            mensaje.getCodigoUsuarioEmisor().getNombre(),
            mensaje.getCodigoUsuarioReceptor().getNombre()
        );
    }
}

