package hn.unah.proyecto.dto;

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

    private String mesInicio;

    private String mesFinal;

    private String anioInicio;

    private String anioFinal;

    private String descripcion;

    private UsuariosDTO usuario;

    private TipoEmpleos tipoEmpleos;
    
    private Empresas empresas;
}
