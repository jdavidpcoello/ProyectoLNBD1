package hn.unah.proyecto.dto;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import hn.unah.proyecto.entidades.Conexiones;
import hn.unah.proyecto.entidades.Empresas;
import hn.unah.proyecto.entidades.EstadoConexion;
import hn.unah.proyecto.entidades.Usuarios;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioConEstadoDTO {

    private int codigoUsuario;
    private String nombre;
    private String apellidos;
    private String titular;
    private String sector;
    private String fotoPerfil;
    private String fotoPortada;
    private String fotoTitularUrl;

    private int codigoConexion;
    private int estadoConexion;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime fechaConexion;

    public UsuarioConEstadoDTO(Usuarios usuario, EstadoConexion estadoConexion, Conexiones conexion, Empresas empresa) {
        this.codigoUsuario = usuario.getCodigoUsuario();
        this.nombre = usuario.getNombre();
        this.apellidos = usuario.getApellidos();
        this.titular = usuario.getTitular();
        this.sector = usuario.getSector();
        this.fotoPerfil = usuario.getFotoPerfil();
        this.fotoPortada = usuario.getFotoPortada();
        this.fotoTitularUrl = empresa.getFotoEmpresa();
        this.estadoConexion = estadoConexion != null ? estadoConexion.getCodigoEstado() : null;
        this.codigoConexion = conexion.getCodigoConexion();
        this.fechaConexion = conexion.getFechaConexion() != null ? conexion.getFechaConexion() : null;
    }
}