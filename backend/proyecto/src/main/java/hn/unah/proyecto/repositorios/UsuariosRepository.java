package hn.unah.proyecto.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import hn.unah.proyecto.entidades.Usuarios;

public interface UsuariosRepository extends JpaRepository<Usuarios,Integer> {

    
}
