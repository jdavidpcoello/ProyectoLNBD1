package hn.unah.proyecto.entidades;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TBL_ESTADO_CONEXIONES")
public class EstadoConexion {
    
    public EstadoConexion(int pendiente) {
        this.codigoEstado = pendiente;
        this.estado = "Pendiente";
    }

    public static final int ACEPTADA = 1;
    public static final int RECHAZADA = 2;
    public static final int PENDIENTE = 3; 
    public static final int CANCELADA = 4;
    public static final int BLOQUEADA = 5;   

    @Id
    @Column(name = "codigo_estado")
    private int codigoEstado;

    @Column(name ="estado")
    private String estado;
}
