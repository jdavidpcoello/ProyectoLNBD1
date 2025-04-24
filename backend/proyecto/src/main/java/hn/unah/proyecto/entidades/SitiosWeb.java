package hn.unah.proyecto.entidades;

import jakarta.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "tbl_sitios_web")
public class SitiosWeb {
    
    @Id
    @Column(name = "codigo_url")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigoUrl;

    private String url;

    @Column(name="textoEnlace")
    private String textoEnlace;

    @ManyToOne()
    @JsonIgnore()
    @JoinColumn(name="codigo_usuario", referencedColumnName = "codigo_usuario")
    private Usuarios usuario;

    @ManyToOne()
    @JoinColumn(name="tipo_sitio_web",referencedColumnName = "codigo_tipo_web")
    private TiposSitiosWeb tiposSitioWeb;

}
