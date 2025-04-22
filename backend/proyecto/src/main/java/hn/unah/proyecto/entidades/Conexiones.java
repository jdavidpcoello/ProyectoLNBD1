package hn.unah.proyecto.entidades;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tbl_conexiones")
@Entity
public class Conexiones {

    @Id
    @Column(name = "CODIGO_CONEXION")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigoConexion;

    @ManyToOne
    @JoinColumn(name = "CODIGO_USUARIO_1", referencedColumnName = "codigo_usuario")
    private Usuarios usuario1;

    @ManyToOne
    @JoinColumn(name = "CODIGO_USUARIO_2", referencedColumnName = "codigo_usuario")
    private Usuarios usuario2;

    @ManyToOne
    @JoinColumn(name = "CODIGO_ESTADO", nullable = false)
    private EstadoConexion estado;

    @Column(name = "FECHA_CONEXION")
    private LocalDateTime fechaConexion;
}


