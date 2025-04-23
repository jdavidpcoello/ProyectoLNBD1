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
@Table(name = "tbl_tipos_lugar_trabajo")
@Entity
public class TipoLugarTrabajo {

    @Id
    @Column(name = "codigo_tipo_lugar_trabajo")
    private int codigoTipoLugarTrabajo;

    @Column(name = "tipo_lugar_trabajo")
    private String tipoLugarTrabajo;
}
