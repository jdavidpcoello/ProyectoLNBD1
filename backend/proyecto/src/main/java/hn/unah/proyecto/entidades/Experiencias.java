package hn.unah.proyecto.entidades;
import java.time.LocalDate;
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
@Table(name = "tbl_experiencias")
@Entity
public class Experiencias {
    
    @Id
    @Column(name = "codigo_experiencia")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigoExperiencia;

    @Column(name = "cargo")
    private String cargo;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_finalizacion")
    private LocalDate fechaFin;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne()
    @JoinColumn(name = "codigo_usuario", referencedColumnName = "codigo_usuario")
    private Usuarios usuario;

    @ManyToOne()
    @JoinColumn(name = "tipo_empleo", referencedColumnName = "codigo_tipo_empleo")
    private TipoEmpleos tipoEmpleo;

    @ManyToOne()
    @JoinColumn(name = "empresa", referencedColumnName = "codigo_empresas")
    private Empresas empresa;

}
