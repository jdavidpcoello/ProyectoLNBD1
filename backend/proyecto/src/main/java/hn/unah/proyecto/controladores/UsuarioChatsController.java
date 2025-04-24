package hn.unah.proyecto.controladores;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.proyecto.servicios.UsuarioChatsService;
import hn.unah.proyecto.dto.ChatDetalleDTO;
import hn.unah.proyecto.dto.UsuarioChatsDTO;


@RequestMapping("/api/chats")
@CrossOrigin(origins = "*")
@RestController
public class UsuarioChatsController {

    @Autowired
    private UsuarioChatsService usuarioChatsService;

    
    @GetMapping("/{codigoChat}")
    public UsuarioChatsDTO findByCodigoChat(@PathVariable int codigoChat) {

        return usuarioChatsService.findByCodigoChat(codigoChat);
    }

    // @GetMapping("/receptor/{codigoReceptor}")
    // public List<UsuarioChatsDTO> obtenerChatsPorReceptor(@PathVariable int codigoReceptor) {
    //     return usuarioChatsService.obtenerChatsDTOPorReceptor(codigoReceptor);
    // }

    @GetMapping("/receptor/{codigoReceptor}")
    public List<ChatDetalleDTO> obtenerChatsPorReceptor(@PathVariable int codigoReceptor) {
        return usuarioChatsService.obtenerChatsDetallePorReceptor(codigoReceptor);
    }

}
