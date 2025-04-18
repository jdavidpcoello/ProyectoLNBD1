package hn.unah.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CiudadesDTO {
     private int codigoCiudad;

    private String nombreCiudad;

    private CiudadesDTO ciudadPadre;

    private PaisesDTO pais;
}
