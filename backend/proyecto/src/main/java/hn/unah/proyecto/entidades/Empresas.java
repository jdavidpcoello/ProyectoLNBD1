package hn.unah.proyecto.entidades;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
@Table(name="tbl_empresas")
public class Empresas {
    
    @Id
    @Column(name="codigo_empresas")
    private int codigoEmpresas;

    @Column(name="nombre_empresas")
    private String nombreEmpresas;

    @Column(name = "foto_empresa")
    private String fotoEmpresa;

    @JsonIgnore
    @OneToMany(mappedBy = "empresa", fetch = FetchType.LAZY)
    private List<Experiencias> experiencias;
    
}
