package hn.unah.proyecto.entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "tbl_ciudades")
@Entity
public class Ciudades {
    
    @Id
    @Column(name = "codigo_ciudad")
    private int codigoCiudad;

    @Column(name = "nombre_ciudad")
    private String nombreCiudad;

    @ManyToOne()
    @JoinColumn(name = "ciudad_padre", referencedColumnName = "codigo_ciudad")
    private Ciudades ciudadPadre;

    @ManyToOne()
    @JoinColumn(name = "pais",referencedColumnName = "codigo_pais")
    private Paises pais;
}
