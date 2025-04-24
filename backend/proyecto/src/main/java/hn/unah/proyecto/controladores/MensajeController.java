package hn.unah.proyecto.controladores;

import java.time.LocalDate;
// import java.util.List;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

// import hn.unah.proyecto.dto.UsuarioChatsDTO;
import hn.unah.proyecto.dto.MensajeDTO;
import hn.unah.proyecto.entidades.Mensajes;
import hn.unah.proyecto.entidades.Usuarios;
import hn.unah.proyecto.servicios.MensajeService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/mensajes")
public class MensajeController {

    @Autowired
    private MensajeService mensajeService;
    @GetMapping("/chat/{codigoMensaje}")
    public List<MensajeDTO> obtenerMensajeDTO(@PathVariable int codigoMensaje) {
        return mensajeService.obtenerMensajesPorChat(codigoMensaje);
    }

} 