package hn.unah.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.unah.proyecto.entidades.Experiencias;

public interface ExperienciasRepository extends JpaRepository<Experiencias, Integer> {
    
}
