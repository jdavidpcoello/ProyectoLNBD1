package hn.unah.proyecto.entidades;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "TBL_MENSAJES")

public class Mensajes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    @Column(name = "CODIGO_MENSAJE")
    private int codigoMensaje;

    @Lob
    @Column(name = "MENSAJE")
    private String mensaje;

    @Column(name = "FECHA_MENSAJE")
    private LocalDate fechaMensaje;

    @Column(name = "CODIGO_CHAT")
    private int codigoChat;

    @OneToOne()
    @JoinColumn(name = "MENSAJE_PADRE", referencedColumnName = "CODIGO_MENSAJE")
    private Mensajes mensajePadre;

    @ManyToOne()
    @JoinColumn(name = "CODIGO_EMISOR", referencedColumnName = "CODIGO_USUARIO")
    private Usuarios UsuarioEmisor;

    @ManyToOne()
    @JoinColumn(name = "CODIGO_RECEPTOR", referencedColumnName = "CODIGO_USUARIO")
    private Usuarios UsuarioReceptor;

}
