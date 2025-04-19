package hn.unah.proyecto.dto;

import java.time.LocalDate;

import hn.unah.proyecto.entidades.Empresas;
import hn.unah.proyecto.entidades.TipoEmpleos;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExperienciasDTO {
    private int codigoExperiencia;

    private String cargo;

    private LocalDate fechaInicio;

    private LocalDate fechaFinalizacion;

    private String descripcion;

    private UsuariosDTO usuario;

    private TipoEmpleos tipoEmpleos;
    
    private Empresas empresas;
}
