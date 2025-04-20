package hn.unah.proyecto.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import hn.unah.proyecto.dto.MensajeDTO;
import hn.unah.proyecto.servicios.MensajeService;

@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;

    @GetMapping("/{codigoMensaje}")
    public MensajeDTO obtenerMensajeDTO(@PathVariable int codigoMensaje) {
        return mensajeService.obtenerMensajeDTO(codigoMensaje);
    }

    @GetMapping("/todos")
    public List<MensajeDTO> obtenerTodosLosMensajes() {
        return mensajeService.obtenerTodosLosMensajes();
    }
} 