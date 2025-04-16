
package hn.unah.proyecto.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.entidades.EstadoConexion;
import hn.unah.proyecto.repositorios.EstadoConexionRepository;


@Service
public class EstadoConexionService {
    
    @Autowired
    private EstadoConexionRepository estadoConexionRepository;

    public EstadoConexion findByEstado(String estado) {
        return estadoConexionRepository.findByEstado(estado);
    }



}
