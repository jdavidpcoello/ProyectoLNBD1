
package hn.unah.proyecto.servicios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.dto.ConexionDTO;
import hn.unah.proyecto.dto.UsuarioConEstadoDTO;
import hn.unah.proyecto.entidades.Conexiones;
import hn.unah.proyecto.entidades.EstadoConexion;
import hn.unah.proyecto.repositorios.ConexionRepository;
import hn.unah.proyecto.repositorios.EstadoConexionRepository;
import hn.unah.proyecto.entidades.Usuarios;
import hn.unah.proyecto.repositorios.UsuariosRepository;

@Service
public class ConexionService {
    
    @Autowired
    private ConexionRepository conexionRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private EstadoConexionRepository estadoConexionRepository;

    public ConexionDTO guardarConexion(ConexionDTO dto) {
        EstadoConexion estado = estadoConexionRepository.findById(dto.getEstado())
            .orElseThrow(() -> new RuntimeException("Estado no encontrado con código: " + dto.getEstado()));

        Conexiones conexion = new Conexiones();
        conexion.setUsuario1Id(dto.getUsuario1Id());
        conexion.setUsuario2Id(dto.getUsuario2Id());
        conexion.setEstado(estado);
        conexion.setFechaConexion(LocalDateTime.now());

        conexion = conexionRepository.save(conexion);

        return new ConexionDTO(conexion);
    }

    public void eliminarConexion(int id){
        Conexiones conexion = this.conexionRepository.findById(id).orElse(null);
        if (conexion != null) {
            this.conexionRepository.delete(conexion);
        }
    }

    public Conexiones obtenerConexionPorId(int id) {
        return conexionRepository.findById(id).orElse(null);
    }
        
    public List<UsuarioConEstadoDTO> obtenerAmigosPorUsuario(int codigoUsuario) {
        List<Conexiones> conexiones = this.conexionRepository.findConexionesPorUsuario(codigoUsuario);
    
        List<UsuarioConEstadoDTO> amigos = new ArrayList<>();
    
        for (Conexiones conexion : conexiones) {
            if (conexion.getEstado().getCodigoEstado() == EstadoConexion.ACEPTADA) {
                int amigoId = (conexion.getUsuario1Id() == codigoUsuario) 
                    ? conexion.getUsuario2Id() 
                    : conexion.getUsuario1Id();
    
                Usuarios amigo = this.usuariosRepository.findById(amigoId).orElse(null);
                if (amigo != null) {
                   
                    UsuarioConEstadoDTO dto = new UsuarioConEstadoDTO(amigo, conexion.getEstado(), conexion);
                    amigos.add(dto);
                }
            }
        }
    
        return amigos;
    }
    
    public List<UsuarioConEstadoDTO> obtenerUsuariosNoAmigosConEstado(int codigoUsuario) {
        List<Conexiones> conexiones = conexionRepository.findConexionesPorUsuario(codigoUsuario);
        
        Map<Integer, Integer> mapaEstados = new HashMap<>();
        for (Conexiones c : conexiones) {
            int otroId = (c.getUsuario1Id() == codigoUsuario) ? c.getUsuario2Id() : c.getUsuario1Id();
            mapaEstados.put(otroId, c.getEstado().getCodigoEstado());
        }

        List<Usuarios> todos = usuariosRepository.findAll();
        List<UsuarioConEstadoDTO> resultado = new ArrayList<>();

        for (Usuarios usuario : todos) {
            if (usuario.getCodigoUsuario() != codigoUsuario) {
                int estado = mapaEstados.getOrDefault(usuario.getCodigoUsuario(), 0);

                if (estado != EstadoConexion.ACEPTADA) {
                    UsuarioConEstadoDTO dto = new UsuarioConEstadoDTO();
                    dto.setCodigoUsuario(usuario.getCodigoUsuario());
                    dto.setNombre(usuario.getNombre());
                    dto.setApellidos(usuario.getApellidos());
                    dto.setTitular(usuario.getTitular());
                    dto.setSector(usuario.getSector());
                    dto.setFotoPerfil(usuario.getFotoPerfil());
                    dto.setFotoPortada(usuario.getFotoPortada());
                    dto.setFotoTitularUrl(usuario.getFotoTitularUrl());
                    dto.setEstadoConexion(estado);
                    
                    Conexiones conexion = conexiones.stream()
                        .filter(c -> c.getUsuario1Id() == usuario.getCodigoUsuario() || c.getUsuario2Id() == usuario.getCodigoUsuario())
                        .findFirst()
                        .orElse(null);
                        
                    if (conexion != null) {
                        dto.setCodigoConexion(conexion.getCodigoConexion());
                    } else {
                        dto.setCodigoConexion(0);
                    }

                    resultado.add(dto);
                }
            }
        }
        return resultado;
    }    
}
