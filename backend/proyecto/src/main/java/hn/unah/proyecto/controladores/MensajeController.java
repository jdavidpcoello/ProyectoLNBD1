package hn.unah.proyecto.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import hn.unah.proyecto.dto.MensajeDTO;
import hn.unah.proyecto.servicios.MensajeService;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @GetMapping("/{codigo}")
    public MensajeDTO obtenerMensajeDTO(@PathVariable int codigo) {
        return mensajeService.obtenerMensajeDTO(codigo);
    }
}