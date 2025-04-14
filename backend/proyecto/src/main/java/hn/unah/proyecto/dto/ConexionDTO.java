package hn.unah.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConexionDTO {

    private int id;
    
    private int usuario1Id;
    
    private int usuario2Id;
    
    private int estado;
    
    private String fechaConexion;

}
