package hn.unah.proyecto.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.unah.proyecto.entidades.Educacion;

public interface EducacionRepository extends JpaRepository<Educacion, Integer> {
    
    public List<Educacion> findAllByUsuarioCodigoUsuario(int codigoUsuario);

    
}
