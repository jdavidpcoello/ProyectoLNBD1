package hn.unah.proyecto.servicios;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import hn.unah.proyecto.dto.MensajeDTO;
import hn.unah.proyecto.entidades.Mensajes;
import hn.unah.proyecto.repositorios.MensajesRepository;

@Service
public class MensajeService {

    @Autowired
    private MensajesRepository mensajesRepository;

    public MensajeDTO obtenerMensajeDTO(int codigoMensaje) {
        Mensajes mensaje ;
        try {
            mensaje = mensajesRepository.findByCodigoMensaje(codigoMensaje);
            System.out.println("Se buscó con codigo mensaje: " + codigoMensaje);
            if (mensaje == null) {
            System.out.println("/n/nNo se encontró ningún mensaje con el código: " + codigoMensaje);
            return null;
        }

        return new MensajeDTO(
            mensaje.getMensaje(),
            mensaje.getFechaMensaje(),
            mensaje.getCodigoUsuarioEmisor().getNombre(),
            mensaje.getCodigoUsuarioReceptor().getNombre()
        );
        } catch (Exception e) {
            System.err.println("Error al buscar el mensaje: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    public List<MensajeDTO> obtenerTodosLosMensajes() {
        return mensajesRepository.findAll().stream()
            .map(m -> new MensajeDTO(
                m.getMensaje(),
                m.getFechaMensaje(),
                m.getCodigoUsuarioEmisor().getNombre(),
                m.getCodigoUsuarioReceptor().getNombre()
                
            ))
            .collect(Collectors.toList());
    }
    
}

