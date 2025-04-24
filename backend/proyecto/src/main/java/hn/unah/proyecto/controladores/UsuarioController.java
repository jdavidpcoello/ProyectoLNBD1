package hn.unah.proyecto.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.proyecto.dto.ActualizarUsuarioDTO;
import hn.unah.proyecto.dto.NewUserDTO;
import hn.unah.proyecto.dto.UsuariosDTO;
import hn.unah.proyecto.entidades.Usuarios;
import hn.unah.proyecto.servicios.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;





@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/signin")
    public UsuariosDTO iniciarSesion(@RequestParam String email, @RequestParam String password) {
        return usuarioService.iniciarSesion(email, password);
    }

    @PostMapping("/login")
    public UsuariosDTO registrarUsuario(@RequestBody NewUserDTO nvoUsuario){
        return usuarioService.registrarUsuario(nvoUsuario);
    }

    @GetMapping("/{codigoUsuario}")
    public Usuarios obtenerUsuarioPorId(@PathVariable int codigoUsuario) {
        return usuarioService.obtenerUsuarioPorId(codigoUsuario);
    }

    @PutMapping("/newProfilePhoto")
    public UsuariosDTO cambiarFotoPerfil(@RequestParam int codigoUsuario, @RequestParam String fotoPerfil){
        return usuarioService.cambiarFotoPerfil(codigoUsuario, fotoPerfil);
    }

    @PutMapping("/newBackgroundPhoto")
    public UsuariosDTO cambiarFotoPortada(@RequestParam int codigoUsuario, @RequestParam String fotoPortada){
        return usuarioService.cambiarFotoPortada(codigoUsuario, fotoPortada);
    }

    @PutMapping("/actualizar")
    public UsuariosDTO actualizarUsuario(@RequestBody ActualizarUsuarioDTO usuario){
        return usuarioService.actualizarUsuario(usuario);
    }
}
