/*package hn.unah.proyecto.servicios;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
// import org.springframework.web.bind.annotation.PathVariable;


import hn.unah.proyecto.repositorios.UsuarioChatsRepository;
import hn.unah.proyecto.dto.UsuarioChatsDTO;
import hn.unah.proyecto.entidades.Usuarios;

@Service
public class UsuarioChatsService {

    @Autowired
    private UsuarioChatsRepository usuarioChatsRepository;

    
    public List<UsuarioChatsDTO> findByCodigoChat(int codigoChat) {
        return usuarioChatsRepository.findByCodigoChat(codigoChat).stream()
            .map(usuarioChat -> {
                Usuarios emisor = usuarioChat.getEmisor();
                return new UsuarioChatsDTO(usuarioChat, emisor);
            })
            .collect(Collectors.toList());
    }

} */

