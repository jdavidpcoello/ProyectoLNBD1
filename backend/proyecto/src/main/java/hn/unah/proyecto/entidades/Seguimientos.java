package hn.unah.proyecto.entidades;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;



@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_seguimientos")
public class Seguimientos {
    
    @Id
    @Column(name = "CODIGO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo;

    @ManyToOne
    @JoinColumn(name = "CODIGO_SEGUIDO", nullable = false)
    private Usuarios usuarioSeguido;

    @ManyToOne
    @JoinColumn(name = "CODIGO_SEGUIDOR", nullable = false)
    private Usuarios usuarioSeguidor;

    @Column(name = "FECHA_SEGUIDOR")
    private LocalDateTime fechaSeguidor;
    
    @Column(name = "ESTADO_SEGUIDOR")
    private int estadoSeguidor;

}
