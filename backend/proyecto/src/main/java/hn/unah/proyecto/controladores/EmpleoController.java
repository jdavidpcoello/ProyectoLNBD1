package hn.unah.proyecto.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.proyecto.dto.EmpleoSugerenciaDTO;
import hn.unah.proyecto.servicios.EmpleoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/empleos")
public class EmpleoController {

    @Autowired
    private EmpleoService empleoService;
    
    @GetMapping("/{codigoEmpleo}")
    public EmpleoSugerenciaDTO obtenerEmpleoSugerenciaDTO(@PathVariable int codigoEmpleo) {
        return empleoService.obtenerEmpleoSugerenciaDTO(codigoEmpleo);
    }

    @GetMapping("/recomendados/{codigoUsuario}")
    public List<EmpleoSugerenciaDTO> obtenerEmpleosRecomendados(@PathVariable int codigoUsuario) {
        return empleoService.obtenerEmpleosExceptoDelUsuario(codigoUsuario);
    }
}
