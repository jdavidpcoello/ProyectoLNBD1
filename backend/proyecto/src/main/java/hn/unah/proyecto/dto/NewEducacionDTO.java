package hn.unah.proyecto.dto;

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
public class NewEducacionDTO {
    private String titulo;
    
    private String disciplinaAcademica;

    private String anioInicio;

    private String anioFinal;
    
    private String mesInicio;

    private String mesFinal;

    private String nota;

    private String actividadesGrupos;

    private String institucionEducativa;

    private int codigoUsuario;

    private String descripcion;
}
