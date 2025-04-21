package hn.unah.proyecto.entidades;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name = "tbl_publicaciones")
@Entity
public class Publicaciones {
    
    @Id
    @Column(name = "codigo_publicacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigoPublicacion;

    @ManyToOne
    @JoinColumn(name = "codigo_usuario", referencedColumnName = "codigo_usuario")
    private Usuarios usuario;
    
    @Column(name = "texto_descripcion")
    private String textoDescripcion;
        
    @Column(name = "colaboracion_marca")
    private int colaboracionMarca;

    @ManyToOne
    @JoinColumn(name = "codigo_visibilidad", referencedColumnName = "codigo_visibilidad")
    private Visibilidad codigoVisibilidad;

    @Column(name = "fecha_publicacion")
    private LocalDateTime fechaPublicacion;

}
