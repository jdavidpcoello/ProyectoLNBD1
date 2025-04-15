package hn.unah.proyecto.dto;

import java.util.Date;

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
    
    private int usuario1Id;
    
    private int usuario2Id;
    
    private int estado;
    
    private Date fechaConexion;

}
