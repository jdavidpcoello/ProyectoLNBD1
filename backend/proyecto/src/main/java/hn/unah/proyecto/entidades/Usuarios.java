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
@Table(name = "tbl_usuarios")
@Entity
public class Usuarios {

    @Id
    @Column(name = "codigo_usuario")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigoUsuario;

    private String nombre;
    
    private String apellidos;

    @Column(name = "nombre_adicional")
    private String nombreAdicional;

    private String titular;

    private String sector;

    @Column(name = "url_perfil")
    private String urlPerfil;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    private String email;

    private String contrasenia;

    private String visibilidad;

    @Column(name = "foto_perfil")
    private String fotoPerfil;

    @Column(name = "foto_portada")
    private String fotoPortada;

    @ManyToOne()
    @JoinColumn(name = "pais", referencedColumnName = "codigo_pais")
    private Paises pais;

    @ManyToOne()
    @JoinColumn(name = "ciudad", referencedColumnName = "codigo_ciudad")
    private Ciudades ciudad;
}

