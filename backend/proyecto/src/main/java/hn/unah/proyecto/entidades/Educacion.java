package hn.unah.proyecto.entidades;

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
@Table(name = "tbl_educacion")
@Entity
public class Educacion {

    @Id
    @Column(name = "codigo_educacion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigoEducacion;

    private String titulo;
    
    @Column(name = "disciplina_academica")
    private String disciplinaAcademica;

    @Column(name = "mes_inicio")
    private String mesInicio;

    @Column(name = "mes_final")
    private String mesFinal;


    @Column(name="anio_inicio")
    private String anioInicio;

    @Column(name="anio_final")
    private String anioFinal;

    private String nota;

    @Column(name = "actividades_grupos")
    private String actividadesGrupos;

    @ManyToOne()
    @JoinColumn(name = "codigo_usuario",referencedColumnName = "codigo_usuario")
    private Usuarios usuario;

    @ManyToOne()
    @JoinColumn(name = "institucion_educativa", referencedColumnName = "codigo_institucion_educativa")
    private Instituciones institucionEducativa;

    private String descripcion;
}
