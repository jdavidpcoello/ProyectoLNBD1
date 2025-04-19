package hn.unah.proyecto.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
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
@Table(name = "tbl_empleos")
@Entity
public class Empleos {
    @Id
    @Column(name = "codigo_publicacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigoEmpleo;

    @OneToOne
    @MapsId
    @JoinColumn(name = "codigo_publicacion")
    private Publicaciones codigoPublicacion;
    
    @Column(name = "cargo")
    private String cargo;

    @ManyToOne()
    @JoinColumn(name = "tipo_empleo", referencedColumnName = "codigo_tipo_empleo")
    private TipoEmpleos tipoEmpleo;

    @ManyToOne()
    @JoinColumn(name = "empresa" , referencedColumnName = "codigo_empresas")
    private Empresas codigoEmpresa;
    
    @ManyToOne()
    @JoinColumn(name = "tipo_lugar_trabajo", referencedColumnName = "codigo_tipo_lugar_trabajo")
    private TipoLugarTrabajo tipoLugarTrabajo;

    @ManyToOne()
    @JoinColumn(name = "ubicacion_empleo", referencedColumnName = "codigo_ciudad")
    private Ciudades ubicacionEmpleo;

    @Column(name = "descripcion_empleo")
    private String descripcionEmpleo;

    @Column(name = "medio_solicitudes")
    private String medioSolicitudes;

}
