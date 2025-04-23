package hn.unah.proyecto.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.proyecto.dto.EducacionDTO;
import hn.unah.proyecto.dto.NewEducacionDTO;
import hn.unah.proyecto.servicios.EducacionService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/educacion")
@CrossOrigin(origins = "*")
public class EducacionController {
    
    @Autowired
    private EducacionService educacionService;

    @PostMapping("/usuario/obtenertodo")
    public List <EducacionDTO> educacionUsuario(@RequestParam int codigoUsuario) {
        return educacionService.educacionUsuario(codigoUsuario);
    }
    
    @PostMapping("/nuevo")
    public EducacionDTO nuevoEducacionUsuario(@RequestBody NewEducacionDTO nvoRegistro){
        return educacionService.nuevoEducacionUsuario(nvoRegistro);
    }


}
