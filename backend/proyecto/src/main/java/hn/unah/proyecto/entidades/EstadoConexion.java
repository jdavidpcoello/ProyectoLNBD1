package hn.unah.proyecto.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.*;

@Entity
@Table(name = "TBL_ESTADO_CONEXIONES")
public class EstadoConexion {
    
    public static final int ACEPTADA = 1;
    public static final int RECHAZADA = 2;
    public static final int PENDIENTE = 3; 
    public static final int CANCELADA = 4;
    public static final int BLOQUEADA = 5;   

    @Id
    @Column(name = "CODIGO_ESTADO")
    private int codigoEstado;

    @Column(name ="Estado")
    private String estado;

}
