
package hn.unah.proyecto.servicios;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.dto.ConexionDTO;
import hn.unah.proyecto.dto.SolicitudDTO;
import hn.unah.proyecto.dto.UsuarioConEstadoDTO;
import hn.unah.proyecto.entidades.Conexiones;
import hn.unah.proyecto.entidades.Educacion;
import hn.unah.proyecto.entidades.Empresas;
import hn.unah.proyecto.entidades.EstadoConexion;
import hn.unah.proyecto.entidades.Experiencias;
import hn.unah.proyecto.entidades.Instituciones;
import hn.unah.proyecto.repositorios.ConexionRepository;
import hn.unah.proyecto.repositorios.EstadoConexionRepository;
import hn.unah.proyecto.repositorios.ExperienciaRepository;
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

    @Autowired
    private ExperienciaRepository experienciaRepository;

    public ConexionDTO guardarConexion(ConexionDTO dto) {
        EstadoConexion estado = estadoConexionRepository.findById(dto.getEstado())
            .orElseThrow(() -> new RuntimeException("Estado no encontrado con código: " + dto.getEstado()));
    
        Usuarios usuario1 = new Usuarios();
        usuario1.setCodigoUsuario(dto.getUsuario1());
    
        Usuarios usuario2 = new Usuarios();
        usuario2.setCodigoUsuario(dto.getUsuario2());
    
        Conexiones conexion = new Conexiones();
        conexion.setUsuario1(usuario1);
        conexion.setUsuario2(usuario2);
        conexion.setEstado(estado);
        conexion.setFechaConexion(LocalDateTime.now());
    
        conexion = conexionRepository.save(conexion);

        Empresas empresa = null;
        Instituciones institucion = null;
        if (!usuario1.getExperiencias().isEmpty()) {
            List<Experiencias> experiencias = usuario1.getExperiencias();
            empresa = experiencias.get(experiencias.size() - 1).getEmpresa();
        }
        if (!usuario1.getEducacion().isEmpty()) {
            List<Educacion> educacion = usuario1.getEducacion();
            institucion = educacion.get(educacion.size() - 1).getInstitucionEducativa();
        }

    
        return new ConexionDTO(conexion, institucion, empresa);
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
        List<Conexiones> conexiones = this.conexionRepository.findConexionesPorUsuario1(codigoUsuario);
    
        List<UsuarioConEstadoDTO> amigos = new ArrayList<>();
    
        for (Conexiones conexion : conexiones) {
            if (conexion.getEstado().getCodigoEstado() == EstadoConexion.ACEPTADA) {
                int amigoId = (conexion.getUsuario1().getCodigoUsuario() == codigoUsuario) 
                    ? conexion.getUsuario2().getCodigoUsuario() 
                    : conexion.getUsuario1().getCodigoUsuario();
    
                Usuarios amigo = this.usuariosRepository.findById(amigoId).orElse(null);
                if (amigo != null) {
                   
                    List<Experiencias> experiencias = this.experienciaRepository.findUltimaExperienciaPorUsuario(amigoId);
                    Empresas empresa = null;
        
                    if (!experiencias.isEmpty()) {
                        Experiencias ultima = experiencias.get(0);
                        empresa = ultima.getEmpresa();                     
                    }

                    Instituciones institucion = null;
                    if (!amigo.getEducacion().isEmpty()) {
                        List<Educacion> educacion = amigo.getEducacion();
                        institucion = educacion.get(educacion.size() - 1).getInstitucionEducativa();
                    }

                    UsuarioConEstadoDTO dto = new UsuarioConEstadoDTO(amigo, conexion.getEstado(), conexion, empresa, institucion);
                    amigos.add(dto);
                }
            }
        }
    
        return amigos;
    }
    
    public List<UsuarioConEstadoDTO> obtenerUsuariosNoAmigosConEstado(int codigoUsuario) {
        List<Conexiones> conexiones = conexionRepository.findConexionesPorUsuario1(codigoUsuario);
        
        Map<Integer, Integer> mapaEstados = new HashMap<>();
        for (Conexiones c : conexiones) {
            int otroId = (c.getUsuario1().getCodigoUsuario() == codigoUsuario) ? c.getUsuario2().getCodigoUsuario() : c.getUsuario1().getCodigoUsuario();
            mapaEstados.put(otroId, c.getEstado().getCodigoEstado());
        }

        List<Usuarios> todos = usuariosRepository.findAll();
        List<UsuarioConEstadoDTO> resultado = new ArrayList<>();

        for (Usuarios usuario : todos) {
            if (usuario.getCodigoUsuario() != codigoUsuario) {

                Conexiones conexion = conexiones.stream()
                    .filter(c -> (c.getUsuario1().getCodigoUsuario() == usuario.getCodigoUsuario() && c.getUsuario2().getCodigoUsuario() == codigoUsuario) || 
                                  c.getUsuario2().getCodigoUsuario() == usuario.getCodigoUsuario() && c.getUsuario1().getCodigoUsuario() == codigoUsuario)
                    .findFirst()
                    .orElse(null);

                    int estado = (conexion != null) ? conexion.getEstado().getCodigoEstado() : 0;

                if (estado == 0 || (conexion != null && estado == EstadoConexion.PENDIENTE && conexion.getUsuario1().getCodigoUsuario() == codigoUsuario)) {

                    UsuarioConEstadoDTO dto = new UsuarioConEstadoDTO();
                    dto.setCodigoUsuario(usuario.getCodigoUsuario());
                    dto.setNombre(usuario.getNombre());
                    dto.setApellidos(usuario.getApellidos());
                    dto.setTitular(usuario.getTitular());
                    dto.setSector(usuario.getSector());
                    dto.setFotoPerfil(usuario.getFotoPerfil());
                    dto.setFotoPortada(usuario.getFotoPortada());
                    dto.setEstadoConexion(estado);
                    dto.setCodigoConexion(conexion != null ? conexion.getCodigoConexion() : 0);
                                        
                    Optional<Experiencias> experienciaConEmpresa = this.experienciaRepository.findUltimaExperienciaPorUsuario(usuario.getCodigoUsuario()).stream()
                        .filter(e -> e.getEmpresa() != null)
                        .findFirst();

                    dto.setFotoEmpresa(experienciaConEmpresa.map(e -> e.getEmpresa().getFotoEmpresa()).orElse(null));

                    
                    resultado.add(dto);
                }
            }
        }
        return resultado;
    }

    public List<SolicitudDTO> obtenerSolicitudesRecibidas(int codigoUsuario) {
        List<Conexiones> conexiones = conexionRepository.findByUsuario2CodigoUsuarioAndEstadoCodigoEstado(codigoUsuario, 3);
        
        List<SolicitudDTO> solicitudes = new ArrayList<>();
        
        for (Conexiones conexion : conexiones) {
            Usuarios usuario1 = conexion.getUsuario1();

            Empresas empresa = null;
            if (!usuario1.getExperiencias().isEmpty()) {
                List<Experiencias> experiencias = usuario1.getExperiencias();
                empresa = experiencias.get(experiencias.size() - 1).getEmpresa();
            }

            Instituciones institucion = null;
            if (!usuario1.getEducacion().isEmpty()) {
                List<Educacion> educacion = usuario1.getEducacion();
                institucion = educacion.get(educacion.size() - 1).getInstitucionEducativa();
            }

            solicitudes.add(new SolicitudDTO(usuario1, empresa, institucion, conexion));
        }

        return solicitudes;
    }
   

    public void aceptarSolicitud(int codigoConexion) {
        Conexiones conexion = conexionRepository.findById(codigoConexion)
            .orElseThrow(() -> new RuntimeException("No encontrada"));
    
        EstadoConexion estadoAceptado = estadoConexionRepository.findById(1)
            .orElseThrow(() -> new RuntimeException("Estado no válido"));
    
        conexion.setEstado(estadoAceptado);
        conexionRepository.save(conexion);
    }

    public void rechazarSolicitud(int codigoConexion) {
        Conexiones conexion = conexionRepository.findById(codigoConexion)
            .orElseThrow(() -> new RuntimeException("No encontrada"));
    
        EstadoConexion estadoRechazado = estadoConexionRepository.findById(3)
            .orElseThrow(() -> new RuntimeException("Estado no válido"));
    
        conexion.setEstado(estadoRechazado);
        conexionRepository.save(conexion);
    }
}
