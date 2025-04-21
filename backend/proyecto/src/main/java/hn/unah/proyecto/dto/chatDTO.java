package hn.unah.proyecto.dto;

import hn.unah.proyecto.entidades.UsuariosChats;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
    

@Data
@NoArgsConstructor
@AllArgsConstructor
public class chatDTO{
    private int codigoChat;
    private String statusChat;

    public chatDTO(UsuariosChats usuarioChat){
        this.codigoChat = usuarioChat.getCodigoChat();
        this.statusChat = usuarioChat.getStatusChat().getStatusChat();
    }
}

