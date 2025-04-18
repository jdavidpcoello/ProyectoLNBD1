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
@Table(name = "tbl_empresas")
@Entity
public class Empresas {

    @Id
    @Column(name = "codigo_empresas")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigoEmpresa;

    @Column(name = "nombre_empresas")
    private String nombreEmpresa;
}
