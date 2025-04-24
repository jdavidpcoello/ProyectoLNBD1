package hn.unah.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarUsuarioDTO {
    private String nombre;
    private String apellido;
    private String nombreAdicional;
    private String titular;
    private String ciudad;
    private String ciudadPadre;
    private String pais;
    private String enlace;
    private String tipoWeb;
    private String textoEnlace;
    private String sector;
    private int codigoUsuario;
}
