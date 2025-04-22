package hn.unah.proyecto.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.proyecto.dto.EducacionDTO;
import hn.unah.proyecto.servicios.EducacionService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/educacion")
@CrossOrigin(origins = "*")
public class EducacionController {
    
    @Autowired
    private EducacionService educacionService;

    @GetMapping("/usuario")
    public List <EducacionDTO> educacionUsuario(@RequestParam int codigoUsuario) {
        return educacionService.educacionUsuario(codigoUsuario);
    }
    

}
