package hn.unah.proyecto.dto;

import java.time.LocalDateTime;

import hn.unah.proyecto.entidades.Conexiones;
import hn.unah.proyecto.entidades.Empresas;
import hn.unah.proyecto.entidades.Instituciones;
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
    
    private int usuario1;
    
    private int usuario2;
    
    private int estado;
    
    private LocalDateTime fechaConexion;

    private String nombreEmpresa;
    private String fotoEmpresa;

    private String nombreInstitucion;   
    private String fotoInstitucion;

    public ConexionDTO(Conexiones conexion, Instituciones institucion, Empresas empresa) {
        this.codigoConexion = conexion.getCodigoConexion();
        this.usuario1 = conexion.getUsuario1().getCodigoUsuario();
        this.usuario2 = conexion.getUsuario2().getCodigoUsuario();
        this.estado = conexion.getEstado().getCodigoEstado();
        this.fechaConexion = conexion.getFechaConexion();

        this.nombreEmpresa = (empresa != null) ? empresa.getNombreEmpresas() : null; 
        this.fotoEmpresa = (empresa != null) ? empresa.getFotoEmpresa() : null;

        this.nombreInstitucion = (institucion != null) ? institucion.getNombreInstitucion() : null;

    }
}
