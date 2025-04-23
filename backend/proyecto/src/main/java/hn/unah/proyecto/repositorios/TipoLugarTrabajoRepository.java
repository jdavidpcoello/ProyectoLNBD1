package hn.unah.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.unah.proyecto.entidades.TipoLugarTrabajo;

public interface TipoLugarTrabajoRepository extends JpaRepository<TipoLugarTrabajo,Integer>{
    public TipoLugarTrabajo findByTipoLugarTrabajo(String tipoLugarTrabajo);
}
