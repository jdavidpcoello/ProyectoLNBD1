package hn.unah.proyecto.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatDetalleDTO {

    private int codigoChat;
    private String statusChat;
    private String nombreEmisor;
    private int codigoEmisor;
    private String apellidosEmisor;
    private String fotoPerfil;
    private String titular;

    private String contenidoUltimoMensaje;
    private LocalDate fechaUltimoMensaje;
    private String mensajePadre;

    public ChatDetalleDTO(UsuarioChatsDTO usuarioChatsDTO, MensajeDTO mensajeDTO) {
        this.codigoChat = usuarioChatsDTO.getCodigoChat();
        this.statusChat = usuarioChatsDTO.getStatusChat();
        this.nombreEmisor = usuarioChatsDTO.getNombreEmisor();
        this.codigoEmisor = usuarioChatsDTO.getCodigoEmisor();
        this.apellidosEmisor = usuarioChatsDTO.getApellidosEmisor();
        this.fotoPerfil = usuarioChatsDTO.getFotoPerfil();
        this.titular = usuarioChatsDTO.getTitular();

        this.contenidoUltimoMensaje = mensajeDTO.getContenido();
        this.fechaUltimoMensaje = mensajeDTO.getFecha();
        this.mensajePadre = mensajeDTO.getMensajePadre();
        this.mensajePadre = mensajeDTO.getMensajePadre() != null ? mensajeDTO.getMensajePadre() : "Sin respuesta";
    }
}

