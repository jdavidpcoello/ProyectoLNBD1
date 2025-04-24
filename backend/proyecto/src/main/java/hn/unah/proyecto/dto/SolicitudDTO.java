package hn.unah.proyecto.dto;

import hn.unah.proyecto.entidades.Conexiones;
import hn.unah.proyecto.entidades.Empresas;
import hn.unah.proyecto.entidades.Instituciones;
import hn.unah.proyecto.entidades.Usuarios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDTO {
    
    private int codigoConexion;
    private Usuarios usuario1;
    private Empresas empresa;
    private Instituciones institucion;
    private String nombre;
    private String apellidos;
    private String fotoPerfil;
    private String nombreInstitucion;
    private String titular;
    private String fotoEmpresa;
    private String nombreEmpresa;
    private String fotoInstitucion;

    public SolicitudDTO(Usuarios usuario1, Empresas empresa, Instituciones institucion, Conexiones conexion) {
        this.nombre = usuario1.getNombre();
        this.apellidos = usuario1.getApellidos();
        this.fotoPerfil = usuario1.getFotoPerfil();

        
        this.nombreEmpresa = (empresa != null) ? empresa.getNombreEmpresas() : null;
        this.fotoEmpresa = (empresa != null) ? empresa.getFotoEmpresa() : null;

        this.nombreInstitucion = (institucion != null) ? institucion.getNombreInstitucion() : null;
        
        this.codigoConexion = conexion.getCodigoConexion();
    }
    
}
