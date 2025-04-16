
package hn.unah.proyecto.servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.dto.ConexionDTO;
import hn.unah.proyecto.entidades.Conexiones;
import hn.unah.proyecto.entidades.EstadoConexion;
import hn.unah.proyecto.repositorios.ConexionRepository;

import hn.unah.proyecto.entidades.Usuarios;
import hn.unah.proyecto.repositorios.UsuariosRepository;

@Service
public class ConexionService {
    
    @Autowired
    private ConexionRepository conexionRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    public ConexionDTO guardarConexion(ConexionDTO dto) {
        Conexiones conexion = new Conexiones();
        conexion.setUsuario1Id(dto.getUsuario1Id());
        conexion.setUsuario2Id(dto.getUsuario2Id());
        conexion.setEstado(dto.getEstado());
        conexion.setFechaConexion(dto.getFechaConexion());

        conexion = conexionRepository.save(conexion);

        dto.setFechaConexion(conexion.getFechaConexion());
        return dto;
    }

    public List<ConexionDTO> obtenerConexionesDeUsuario(int idUsuario) {
        List<Conexiones> conexiones = conexionRepository.findByUsuario1Id(idUsuario);
        return conexiones.stream().map(c -> {
            ConexionDTO dto = new ConexionDTO();
            dto.setUsuario1Id(c.getUsuario1Id());
            dto.setUsuario2Id(c.getUsuario2Id());
            dto.setEstado(c.getEstado());
            dto.setFechaConexion(c.getFechaConexion());
            return dto;
        }).collect(Collectors.toList());
    }

    private ConexionDTO convertirADTO(Conexiones c) {
        ConexionDTO dto = new ConexionDTO();
        dto.setCodigoConexion(c.getCodigoConexion());
        dto.setUsuario1Id(c.getUsuario1Id());
        dto.setUsuario2Id(c.getUsuario2Id());
        dto.setEstado(c.getEstado());
        dto.setFechaConexion(c.getFechaConexion());
        return dto;
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

    public List<Usuarios> obtenerAmigosPorUsuario(int codigoUsuario) {
        List<Conexiones> conexiones = this.conexionRepository
            .findConexionesActivasPorUsuarioYEstado(codigoUsuario, EstadoConexion.ACEPTADA);

        List<Usuarios> amigos = new ArrayList<>();
        
        for (Conexiones conexion : conexiones) {
            int amigoId = (conexion.getUsuario1Id() == codigoUsuario) 
                ? conexion.getUsuario2Id() 
                : conexion.getUsuario1Id();
            Usuarios amigo = this.usuariosRepository.findById(amigoId).orElse(null);
            if (amigo != null) {
                amigos.add(amigo);
            }
        }
        return amigos;
    }

}
