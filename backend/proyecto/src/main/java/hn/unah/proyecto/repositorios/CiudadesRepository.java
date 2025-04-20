package hn.unah.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.unah.proyecto.entidades.Ciudades;


public interface CiudadesRepository extends JpaRepository<Ciudades, Integer> {
    
    public Ciudades findByNombreCiudad(String nombreCiudad);
    Ciudades findByNombreCiudadAndCiudadPadre(String nombreCiudad, Ciudades ciudadPadre);
}
