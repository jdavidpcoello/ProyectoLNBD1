package hn.unah.proyecto.servicios;

import java.util.stream.Collectors;

import java.util.List;
// import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.repositorios.MensajesRepository;
import hn.unah.proyecto.repositorios.UsuarioChatsRepository;
import hn.unah.proyecto.dto.ChatDetalleDTO;
import hn.unah.proyecto.dto.MensajeDTO;
import hn.unah.proyecto.dto.UsuarioChatsDTO;
import hn.unah.proyecto.entidades.Mensajes;
// import hn.unah.proyecto.entidades.Usuarios;
import hn.unah.proyecto.entidades.UsuariosChats;

@Service
public class UsuarioChatsService {

    @Autowired
    public UsuarioChatsRepository usuarioChatsRepository;

    @Autowired
    public MensajesRepository mensajesRepository;

    public UsuarioChatsDTO findByCodigoChat(int codigoChat) {
        UsuariosChats entidad = usuarioChatsRepository.findByCodigoChat(codigoChat);
        if (entidad != null) {
            return new UsuarioChatsDTO(entidad, entidad.getReceptor());
        }
        return null;
    }

    // public List<UsuarioChatsDTO> obtenerChatsDTOPorReceptor(int codigoReceptor) {
    //     return usuarioChatsRepository.findChatsDTOByReceptor(codigoReceptor);
    // }


    public List<UsuarioChatsDTO> obtenerChatsDTOPorReceptor(int codigoReceptor) {
        List<UsuariosChats> lista = usuarioChatsRepository.findByIdCodigoUsuario(codigoReceptor);
        return lista.stream()
            .map(uc -> new UsuarioChatsDTO(uc, uc.getReceptor()))
            .collect(Collectors.toList());
    }

    public List<ChatDetalleDTO> obtenerChatsDetallePorReceptor(int codigoReceptor) {
        List<UsuariosChats> lista = usuarioChatsRepository.findByIdCodigoUsuario(codigoReceptor);

        return lista.stream().map(uc -> {
            UsuarioChatsDTO chatsDTO = new UsuarioChatsDTO(uc, uc.getReceptor());

            // Obtener el último mensaje por códigoChat
            Mensajes mensaje = mensajesRepository.findTopByCodigoChatOrderByFechaMensajeDesc(uc.getCodigoChat());
            MensajeDTO mensajeDTO = (mensaje != null) ? new MensajeDTO(mensaje) : new MensajeDTO();

            return new ChatDetalleDTO(chatsDTO, mensajeDTO);
        }).collect(Collectors.toList());
    }


} 

