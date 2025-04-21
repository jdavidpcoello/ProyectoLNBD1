package hn.unah.proyecto.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuariosDTO {

    private int codigoUsuario;

    private String nombre;

    private String apellidos;

    private String nombreAdicional;

    private String titular;

    private String sector;

    private String urlPerfil;

    private LocalDate fechaNacimiento;

    private String email;
    
    private String visibilidad;

    private String fotoPerfil;

    private String fotoPortada;

    private PaisesDTO pais;

    private CiudadesDTO ciudad;
}
