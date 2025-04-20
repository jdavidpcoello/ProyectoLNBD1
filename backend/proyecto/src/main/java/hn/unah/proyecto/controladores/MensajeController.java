package hn.unah.proyecto.controladores;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import hn.unah.proyecto.dto.MensajeDTO;
import hn.unah.proyecto.entidades.Mensajes;
import hn.unah.proyecto.entidades.Usuarios;
import hn.unah.proyecto.servicios.MensajeService;

@CrossOrigin(origins = "http://192.168.0.5:5501")
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

    @PostMapping("/demo")
    public MensajeDTO crearMensajeDemo() {
        Mensajes mensaje = new Mensajes();
        mensaje.setMensaje("¡Hola desde el endpoint de prueba!");
        mensaje.setFechaMensaje(LocalDate.now());
        mensaje.setCodigoChat(123); // Podés usar cualquier número
    
        // 👇 IMPORTANTE: asegurate que existan usuarios con estos IDs
        Usuarios emisor = new Usuarios();
        emisor.setCodigoUsuario(1); // cambia esto si necesitás
    
        Usuarios receptor = new Usuarios();
        receptor.setCodigoUsuario(2); // cambia esto si necesitás
    
        mensaje.setUsuarioEmisor(emisor);
        mensaje.setUsuarioReceptor(receptor);
    
        Mensajes guardado = this.mensajeService.guardarMensaje(mensaje);
    
        return new MensajeDTO(guardado);
    }
} 