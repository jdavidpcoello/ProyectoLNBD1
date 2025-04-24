package hn.unah.proyecto.entidades;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Entity
@Table(name = "tbl_tipos_web")
public class TiposSitiosWeb {
    
    @Id
    @Column(name="codigo_tipo_web")
    private int codigoTipoWeb;

    @Column(name="tipo_sitio_web")
    private String sitioWeb;
}
