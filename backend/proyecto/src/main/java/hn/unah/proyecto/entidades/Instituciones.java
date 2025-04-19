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

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="TBL_INSTITUCIONES_EDUCATIVAS")
public class Instituciones {

    @Id
    @Column(name="codigo_institucion_educativa")
    private int codigoInstitucionEducativa;

    @Column(name="nombre_institucion_educativa")
    private String nombreInstitucion;

}
