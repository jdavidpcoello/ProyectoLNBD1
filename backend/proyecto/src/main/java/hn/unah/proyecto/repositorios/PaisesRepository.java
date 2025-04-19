package hn.unah.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.unah.proyecto.entidades.Paises;


public interface PaisesRepository extends JpaRepository<Paises,String>{
    
    public Paises findByNombre(String nombre);
}
