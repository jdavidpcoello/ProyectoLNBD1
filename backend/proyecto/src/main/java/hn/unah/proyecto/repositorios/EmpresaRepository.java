
package hn.unah.proyecto.repositorios;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import hn.unah.proyecto.entidades.Empresas;

@Repository
public interface EmpresaRepository {

    @Query("SELECT c FROM Empresas c WHERE codigoEmpresa= :codigoEmpresa")
    public Empresas findByCodigoEmpresa(@Param("codigoEmpresa") int codigoEmpresa); 
}
