package hn.unah.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.proyecto.entidades.Paises;

@Repository
public interface PaisesRepository extends JpaRepository<Paises,String>{
    
    public Paises findByNombre(String nombre);
}
