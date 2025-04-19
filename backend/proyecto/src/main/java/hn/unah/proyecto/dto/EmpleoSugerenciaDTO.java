package hn.unah.proyecto.dto;

import hn.unah.proyecto.entidades.Empleos;
import hn.unah.proyecto.entidades.Empresas;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpleoSugerenciaDTO {
    
    private int codigoEmpleo;

    private String fotoEmpresa; 

    private String cargo;

    private String nombreEmpresa;

    private String ubicacionEmpleo;

    private String tipoLugarTrabajo;

    private String tipoEmpleo;
    
    private String descripcionEmpleo;

    private String medioSolicitudes;


    public EmpleoSugerenciaDTO (Empleos empleo, Empresas empresa ) {
        this.codigoEmpleo = empleo.getCodigoEmpleo();
        this.fotoEmpresa = empresa.getFotoEmpresa();
        this.cargo = empleo.getCargo();
        this.nombreEmpresa = empresa.getNombreEmpresas();
        this.ubicacionEmpleo = empleo.getUbicacionEmpleo().getNombreCiudad();
        this.tipoLugarTrabajo = empleo.getTipoLugarTrabajo().getTipoLugarTrabajo();
        this.tipoEmpleo = empleo.getTipoEmpleo().getTipoEmpleo();
        this.descripcionEmpleo = empleo.getDescripcionEmpleo();
        this.medioSolicitudes = empleo.getMedioSolicitudes();
    }
    
}
