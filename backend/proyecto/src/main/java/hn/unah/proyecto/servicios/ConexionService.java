
package hn.unah.proyecto.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


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

    public Conexiones guardarConexion(Conexiones conexion) {
        return conexionRepository.save(conexion);
    }

    public Conexiones obtenerConexionPorId(int id) {
        return conexionRepository.findById(id).orElse(null);
    }

    // public List<Conexiones> obtenerConexionesActivasPorUsuario(int codigoUsuario) {
    //     return conexionRepository.findConexionesActivasPorUsuario(codigoUsuario);
    // }

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
            System.out.println("Amigo encontrado con ID: " + amigoId);

        }
        System.out.println("Buscando amigos para el usuario: " + codigoUsuario);
        System.out.println("Conexiones encontradas: " + conexiones.size());

        System.out.println("Código usuario: " + codigoUsuario + ", amigos encontrados: " + amigos.size());

        return amigos;
    }

}
