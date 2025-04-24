package hn.unah.proyecto.dto;

import java.time.LocalDate;

import hn.unah.proyecto.entidades.Usuarios;
import hn.unah.proyecto.entidades.UsuariosChats;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
    

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioChatsDTO{
    private UsuariosChats usuarioChat;
    private int codigoChat;
    private String statusChat;
    private String nombreEmisor;
    private int codigoEmisor;
    private String apellidosEmisor;
    private String fotoPerfil;
    private String titular;
    private LocalDate fechaUltimoMensaje;

    public UsuarioChatsDTO(UsuariosChats usuarioChat, Usuarios receptor) {
        this.codigoChat = usuarioChat.getCodigoChat();
        this.statusChat = usuarioChat.getStatusChat().getStatusChat();
        this.nombreEmisor = receptor.getNombre();
        this.codigoEmisor = receptor.getCodigoUsuario();
        this.apellidosEmisor = receptor.getApellidos();
        this.fotoPerfil = receptor.getFotoPerfil();
        this.titular = receptor.getTitular();
        
    }


}