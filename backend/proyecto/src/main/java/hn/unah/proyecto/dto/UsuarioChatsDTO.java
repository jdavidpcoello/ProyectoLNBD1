package hn.unah.proyecto.dto;

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
    private Usuarios emisor;
    private String nombreEmisor;
    private int codigoEmisor;
    private String apellidosEmisor;
    private String titular;

    public UsuarioChatsDTO(UsuariosChats usuarioChat, Usuarios emisor) {
        this.codigoChat = usuarioChat.getCodigoChat();
        this.statusChat = usuarioChat.getStatusChat().getStatusChat();
        this.nombreEmisor = emisor.getNombre();
        this.codigoEmisor = emisor.getCodigoUsuario();
        this.apellidosEmisor = emisor.getApellidos();
        this.titular = emisor.getTitular();
    }
}

