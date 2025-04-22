/*package hn.unah.proyecto.controladores;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.proyecto.servicios.UsuarioChatsService;
import hn.unah.proyecto.dto.UsuarioChatsDTO;


@RequestMapping("/api/chats")
@CrossOrigin(origins = "*")
@RestController
public class UsuarioChatsController {

    @Autowired
    private UsuarioChatsService usuarioChatsService;

    // @GetMapping("/chat/{codigoChat}")
    // public MensajeDTO obtenerMensajeDTO(int codigoMensaje) {
    //     try {
    //         Mensajes mensaje = this.usuarioChatsRepository.findById(codigoChat).orElse(null);
    //         MensajeDTO mensajeDTO = new MensajeDTO(mensaje);
    //         System.out.println("MensajeDTO: " + mensajeDTO.toString());
    //         return mensajeDTO;
    //     } catch (Exception e) {
    //         System.err.println("Error al buscar el mensaje: " + e.getMessage());
    //         e.printStackTrace();
    //         return null;
    //     }
        
    // }

    @GetMapping("/{codigoChat}")
    public List<UsuarioChatsDTO> findByCodigoChat(@PathVariable int codigoChat) {

        return usuarioChatsService.findByCodigoChat(codigoChat);
    }

    
}*/

