
package hn.unah.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.proyecto.entidades.Empresas;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresas,Integer> {

    public Empresas findByNombreEmpresas(String nombreEmpresas);
}
