
package hn.unah.proyecto.controladores;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hn.unah.proyecto.entidades.Conexiones;
import hn.unah.proyecto.servicios.ConexionService;
import hn.unah.proyecto.entidades.Usuarios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/conexiones")
@CrossOrigin(origins = "*")
public class ConexionController {
    
    @Autowired
    private ConexionService conexionService;

    @PostMapping
    public ResponseEntity<Conexiones> guardarConexion(@RequestBody Conexiones conexion) {
        Conexiones nuevaConexion = new Conexiones();
        nuevaConexion.setUsuario1Id(conexion.getUsuario1Id());
        nuevaConexion.setUsuario2Id(conexion.getUsuario2Id());
        nuevaConexion.setFechaConexion(conexion.getFechaConexion());
        
        Conexiones conexionGuardada = conexionService.guardarConexion(nuevaConexion);
        return ResponseEntity.ok(conexionGuardada);
    }

    @GetMapping("/amigos/{codigoUsuario}")
    public ResponseEntity<List<Usuarios>> obtenerAmigos(@PathVariable int codigoUsuario) {
        List<Usuarios> amigos = this.conexionService.obtenerAmigosPorUsuario(codigoUsuario);
        if (amigos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(amigos);
    }


}
