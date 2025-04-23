package hn.unah.proyecto.controladores;

import hn.unah.proyecto.dto.ExperienciasDTO;
import hn.unah.proyecto.dto.NewExperienceDTO;
import hn.unah.proyecto.servicios.ExperienciaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/experiencia")
@CrossOrigin(origins = "*")
public class ExperienciaController {

    @Autowired
    private ExperienciaService experienciaService;

    @PostMapping("/usuario/obtenertodos")
    public List<ExperienciasDTO> trabajoUsuario(@RequestParam int codigoUsuario) {
        return experienciaService.trabajoUsuario(codigoUsuario);
    }

    @PostMapping("/nuevo")
    public ExperienciasDTO nuevaExperienciasUsuario(@RequestBody NewExperienceDTO nvoRegistro){
        return experienciaService.nuevaExperienciasUsuario(nvoRegistro);
    }
    

}
