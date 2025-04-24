package hn.unah.proyecto.entidades;

import jakarta.persistence.*;

@Entity
@Table(name = "tbl_usuarios_chats")
public class UsuariosChats {

    @EmbeddedId
    private UsuariosChatsId id;

    @ManyToOne
    @MapsId("codigoUsuario")
    @JoinColumn(name = "codigo_usuario")
    private Usuarios receptor;

    @Column(name = "codigo_chat", insertable = false, updatable = false)
    private int codigoChat;

    @ManyToOne
    @JoinColumn(name = "status_chat", referencedColumnName = "codigo_status")
    private StatusChat statusChat;

    public UsuariosChatsId getId() {
        return id;
    }

    public void setId(UsuariosChatsId id) {
        this.id = id;
    }

    public Usuarios getReceptor() {
        return receptor;
    }

    public void setReceptor(Usuarios receptor) {
        this.receptor = receptor;
    }

    public int getCodigoChat() {
        return codigoChat;
    }

    public void setCodigoChat(int codigoChat) {
        this.codigoChat = codigoChat;
    }

    public StatusChat getStatusChat() {
        return statusChat;
    }

    public void setStatusChat(StatusChat statusChat) {
        this.statusChat = statusChat;
    }
}
