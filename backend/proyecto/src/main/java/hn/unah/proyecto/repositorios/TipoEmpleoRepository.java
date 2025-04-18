package hn.unah.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.unah.proyecto.entidades.TipoEmpleos;

public interface TipoEmpleoRepository extends JpaRepository<TipoEmpleos,Integer>{
    
    public TipoEmpleos findByTipoEmpleo(String tipoEmpleo);

}
