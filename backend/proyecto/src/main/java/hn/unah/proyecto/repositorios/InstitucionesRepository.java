package hn.unah.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.unah.proyecto.entidades.Instituciones;

public interface InstitucionesRepository extends JpaRepository<Instituciones, Integer> {

    public Instituciones findByNombreInstitucion(String nombreInstitucion);

}
