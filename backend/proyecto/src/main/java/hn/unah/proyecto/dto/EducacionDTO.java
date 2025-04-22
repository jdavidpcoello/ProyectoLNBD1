package hn.unah.proyecto.dto;


import java.time.LocalDate;

import hn.unah.proyecto.entidades.Instituciones;
import hn.unah.proyecto.entidades.Usuarios;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EducacionDTO {
    private int codigoEducacion;

    private String titulo;
    
    private String disciplinaAcademica;

    private LocalDate fechaInicio;

    private LocalDate fechaFinal;

    private String anioInicio;

    private String anioFinal;

    private String nota;

    private String actividadesGrupos;

    private Usuarios usuario;

    private Instituciones institucionEducativa;
}
