
package hn.unah.proyecto.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.proyecto.dto.ConexionDTO;
import hn.unah.proyecto.dto.SolicitudDTO;
import hn.unah.proyecto.dto.UsuarioConEstadoDTO;
import hn.unah.proyecto.servicios.ConexionService;
import hn.unah.proyecto.servicios.EstadoConexionService;
import hn.unah.proyecto.entidades.Conexiones;
import hn.unah.proyecto.entidades.EstadoConexion;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/conexiones")
public class ConexionController {
    
    @Autowired
    private ConexionService conexionService;

    @Autowired
    private EstadoConexionService estadoConexionService;

    @PostMapping
    public ResponseEntity<ConexionDTO> crearConexion(@RequestBody ConexionDTO conexionDTO) {
        ConexionDTO nuevaConexion = conexionService.guardarConexion(conexionDTO);
        return ResponseEntity.ok(nuevaConexion);
    }

    @GetMapping("/amigos/{codigoUsuario}")
    public ResponseEntity<List<UsuarioConEstadoDTO>> obtenerAmigos(@PathVariable int codigoUsuario) {
        List<UsuarioConEstadoDTO> amigos = conexionService.obtenerAmigosPorUsuario(codigoUsuario);
        return ResponseEntity.ok(amigos);
    }

    @GetMapping("/sugerencias/{codigoUsuario}")
    public ResponseEntity<List<UsuarioConEstadoDTO>> obtenerPosiblesContactos(@PathVariable int codigoUsuario) {
        List<UsuarioConEstadoDTO> sugerencias = conexionService.obtenerUsuariosNoAmigosConEstado(codigoUsuario);
        return ResponseEntity.ok(sugerencias);
    }

    @PutMapping("/cancelar/{codigoConexion}")
    public ResponseEntity<?> cancelarSolicitud(@PathVariable int codigoConexion) {
        Conexiones conexion = conexionService.obtenerConexionPorId(codigoConexion);
        if (conexion == null) {
            return ResponseEntity.notFound().build();
        }

        EstadoConexion estadoPendiente = estadoConexionService.findByEstado("PENDIENTE");
        if (conexion.getEstado().getCodigoEstado() != estadoPendiente.getCodigoEstado()) {
            return ResponseEntity.badRequest().body("Solo se pueden cancelar solicitudes pendientes");
        }

        conexionService.eliminarConexion(codigoConexion);
        return ResponseEntity.ok("Solicitud cancelada");
    }

    @DeleteMapping("/eliminar/{codigoConexion}")
    public ResponseEntity<?> eliminarConexion(@PathVariable int codigoConexion) {
        Conexiones conexion = conexionService.obtenerConexionPorId(codigoConexion);
        if (conexion == null) {
            return ResponseEntity.notFound().build();
        }

        conexionService.eliminarConexion(codigoConexion);
        return ResponseEntity.ok("Conexión eliminada");
    }

    @GetMapping("/solicitudes/{codigoUsuario}")
    public List<SolicitudDTO> obtenerSolicitudesRecibidas(@PathVariable int codigoUsuario) {
        return conexionService.obtenerSolicitudesRecibidas(codigoUsuario);
    }

    @PutMapping("/aceptar/{codigoConexion}")
    public ResponseEntity<?> aceptarSolicitud(@PathVariable int codigoConexion) {
        conexionService.aceptarSolicitud(codigoConexion);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/rechazar/{codigoConexion}")
    public ResponseEntity<?> rechazarSolicitud(@PathVariable int codigoConexion) {
        conexionService.rechazarSolicitud(codigoConexion);
        return ResponseEntity.ok().build(); 
    }    

}
