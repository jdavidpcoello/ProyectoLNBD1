
package hn.unah.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import hn.unah.proyecto.entidades.Usuarios;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuarios,Integer> {

    public Usuarios findByEmail(String email);
    public boolean existsByEmail(String email);
}
