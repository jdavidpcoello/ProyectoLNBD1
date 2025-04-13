package hn.unah.proyecto.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.proyecto.servicios.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;




@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://192.168.0.11:5500")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/signin")
    public boolean iniciarSesion(@RequestParam String email, @RequestParam String password) {
        return usuarioService.iniciarSesion(email, password);
    }

}
