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

    @Column(name = "CODIGO_USUARIO_1", nullable = false)
    private int usuario1Id;

    @Column(name = "CODIGO_USUARIO_2", nullable = false)
    private int usuario2Id;

    @Column(name = "CODIGO_ESTADO", nullable = false)
    private int estado;

    @Column(name = "FECHA_CONEXION")
    private LocalDateTime fechaConexion;
}


