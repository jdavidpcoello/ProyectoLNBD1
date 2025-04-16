
package hn.unah.proyecto.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.proyecto.entidades.EstadoConexion;

@Repository
public interface EstadoConexionRepository extends JpaRepository<EstadoConexion,Integer> {
   
    public EstadoConexion findByEstado(String estado);
}
