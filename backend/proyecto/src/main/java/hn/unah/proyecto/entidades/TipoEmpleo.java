package hn.unah.proyecto.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "tbl_tipo_empleos")
@Entity
public class TipoEmpleo {

    @Id
    @Column(name = "codigo_tipo_empleo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigoTipoEmpleo;

    @Column(name = "tipo_empleo")
    private String tipoEmpleo; 
}
