package hn.unah.proyecto.entidades;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UsuariosChatsId implements Serializable {

    @Column(name = "codigo_chat")
    private int codigoChat;

    @Column(name = "codigo_usuario")
    private int codigoUsuario;

    public int getCodigoChat() {
        return codigoChat;
    }

    public void setCodigoChat(int codigoChat) {
        this.codigoChat = codigoChat;
    }

    public int getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(int codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UsuariosChatsId)) return false;
        UsuariosChatsId that = (UsuariosChatsId) o;
        return codigoChat == that.codigoChat &&
               codigoUsuario == that.codigoUsuario;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(codigoChat, codigoUsuario);
    }
}
