package hn.unah.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.unah.proyecto.entidades.TiposSitiosWeb;

public interface TiposWebRepository extends JpaRepository<TiposSitiosWeb,Integer> {
    
    public TiposSitiosWeb findBySitioWeb(String sitioWeb);
}
