package hn.unah.proyecto.dto;

import java.time.LocalDate;

import hn.unah.proyecto.entidades.Mensajes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MensajeDTO {
    private String contenido;
    private LocalDate fecha;
    private String emisor;
    private String receptor;

  
    public MensajeDTO(Mensajes mensaje) {
        this.contenido = mensaje.getMensaje();
        this.fecha = mensaje.getFechaMensaje();
        this.emisor = mensaje.getCodigoUsuarioEmisor().getNombre();
        this.receptor = mensaje.getCodigoUsuarioReceptor().getNombre();
    }

   
}
