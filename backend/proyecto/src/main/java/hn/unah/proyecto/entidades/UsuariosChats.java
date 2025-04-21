package hn.unah.proyecto.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Table(name = "tbl_usuarios_chats")
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UsuariosChats {
    @Id
    @Column(name = "codigo_chat")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigoChat;

    @ManyToOne
    @JoinColumn(name = "codigo_usuario", referencedColumnName = "codigo_usuario")
    private Usuarios codigoUsuario;

    @ManyToOne
    @JoinColumn(name = "status_chat", referencedColumnName = "codigo_status")
    private StatusChat statusChat;
}
