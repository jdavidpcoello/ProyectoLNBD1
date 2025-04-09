package hn.unah.proyecto.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.proyecto.entidades.Usuarios;
import hn.unah.proyecto.repositorios.UsuariosRepository;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuariosRepository usuariosRepository;
    
    @GetMapping("/obtener/{id}")
    public Usuarios obetnerUsuario (@PathVariable int id) {
        Optional<Usuarios> usuario = usuariosRepository.findById(id);
        if (usuario.isPresent()) {
            return usuario.get();
        } else {
            return null; 
        }
    }

}
