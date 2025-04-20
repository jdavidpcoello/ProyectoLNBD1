package hn.unah.proyecto.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MensajeDTO {
    private String contenido;
    private LocalDate fecha;
    private String emisor;
    private String receptor;

  
    public MensajeDTO(String contenido, LocalDate fechaMensaje, String emisor, String receptor) {
        this.contenido = contenido;
        this.fecha = fechaMensaje;
        this.emisor = emisor;
        this.receptor = receptor;
    }

   
}
