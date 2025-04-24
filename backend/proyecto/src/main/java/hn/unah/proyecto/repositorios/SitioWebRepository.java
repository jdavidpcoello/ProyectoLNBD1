package hn.unah.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.unah.proyecto.entidades.SitiosWeb;

public interface SitioWebRepository extends JpaRepository<SitiosWeb,Integer> {
    
}
