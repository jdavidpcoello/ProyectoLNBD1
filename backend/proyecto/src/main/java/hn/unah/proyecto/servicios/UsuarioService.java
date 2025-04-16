package hn.unah.proyecto.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.entidades.Usuarios;
import hn.unah.proyecto.repositorios.UsuariosRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuariosRepository usuariosRepository;

    public boolean iniciarSesion(String email,String password) {
        Usuarios usuario = usuariosRepository.findByEmail(email);
        if(usuario==null){
            return false;
        }
        if(!(usuario.getContrasenia().equals(password))){
            return false;
        }
        return true;
    }

    public Usuarios obtenerUsuarioPorId(int codigoUsuario) {
        return usuariosRepository.findById(codigoUsuario).orElse(null);
    }
}
