package hn.unah.proyecto.servicios;


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
        try {
            Mensajes mensaje = this.mensajesRepository.findById(codigoMensaje).orElse(null);
            MensajeDTO mensajeDTO = new MensajeDTO(mensaje);
            System.out.println("MensajeDTO: " + mensajeDTO.toString());
            return mensajeDTO;
        } catch (Exception e) {
            System.err.println("Error al buscar el mensaje: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        
    }


    public List<MensajeDTO> obtenerMensajesPorChat(int codigoChat) {
        try {
            List<Mensajes> mensajes = this.mensajesRepository.findByCodigoChat(codigoChat);
            List<MensajeDTO> mensajesDTO = mensajes.stream()
                    .map(MensajeDTO::new)
                    .collect(Collectors.toList());
            return mensajesDTO;
        } catch (Exception e) {
            System.err.println("Error al buscar los mensajes: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    
    public Mensajes guardarMensaje(Mensajes mensaje) {
        return this.mensajesRepository.save(mensaje);
    }
}

