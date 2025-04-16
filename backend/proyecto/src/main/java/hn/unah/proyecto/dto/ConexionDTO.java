package hn.unah.proyecto.dto;

import java.time.LocalDateTime;

import hn.unah.proyecto.entidades.Conexiones;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConexionDTO {

    private int codigoConexion;
    
    private int usuario1Id;
    
    private int usuario2Id;
    
    private int estado;
    
    private LocalDateTime fechaConexion;

    public ConexionDTO(Conexiones conexion) {
        this.codigoConexion = conexion.getCodigoConexion();
        this.usuario1Id = conexion.getUsuario1Id();
        this.usuario2Id = conexion.getUsuario2Id();
        this.estado = conexion.getEstado();
        this.fechaConexion = conexion.getFechaConexion();
    }
}
