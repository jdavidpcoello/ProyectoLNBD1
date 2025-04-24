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
    private String mensajePadre;
    private String nombreEmisor;
    private String apellidoEmisor;
    private String emisorCargo;
    private String emisorFotoPerfil;
    private int codigoChat;
    private int codigoEmisor;
  
    public MensajeDTO(Mensajes mensaje) {
        this.contenido = mensaje.getMensaje();
        this.fecha = mensaje.getFechaMensaje();
        this.nombreEmisor = mensaje.getUsuarioEmisor().getNombre();
        this.apellidoEmisor = mensaje.getUsuarioEmisor().getApellidos();
        this.emisorCargo = mensaje.getUsuarioEmisor().getTitular();
        this.emisorFotoPerfil = mensaje.getUsuarioEmisor().getFotoPerfil();
        this.codigoChat = mensaje.getCodigoChat();
        this.codigoEmisor = mensaje.getUsuarioEmisor().getCodigoUsuario();

        if (mensaje.getMensajePadre() != null) {
            this.mensajePadre = mensaje.getMensajePadre().getMensaje();
        } else {
            this.mensajePadre = null; 
        }
    }
}