package hn.unah.proyecto.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoDTO {
    
    private int codigo;

    private int usuarioSeguido;

    private int usuarioSeguidor;

    private String fechaSeguidor;

    private int estadoSeguidor;
}
