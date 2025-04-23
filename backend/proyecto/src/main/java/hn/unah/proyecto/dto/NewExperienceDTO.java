package hn.unah.proyecto.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewExperienceDTO {
    private String cargo;
    private String empresa;
    private String mesInicioJob;
    private String anioInicioJob;
    private String mesFinalJob;
    private String anioFinalJob;
    private String tipoEmpleo;
    private String tipoLugarTrabajo;
    private String jobDescription;
    private int codigoUsuario;
}
