package hn.unah.proyecto.dto;

import hn.unah.proyecto.entidades.Usuarios;
import java.time.LocalDate;

public class MensajeDTO {
    private String contenido;
    private LocalDate fecha;
    private String emisor;
    private String receptor;

  
    public MensajeDTO(String contenido, String emisor, String receptor) {
        this.contenido = contenido;
        this.emisor = emisor;
        this.receptor = receptor;
    }

   
    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }
}
