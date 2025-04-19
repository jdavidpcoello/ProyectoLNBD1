package hn.unah.proyecto.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.dto.EmpleoSugerenciaDTO;
import hn.unah.proyecto.entidades.Empleos;
import hn.unah.proyecto.entidades.Empresas;
import hn.unah.proyecto.repositorios.EmpleoRepository;

@Service
public class EmpleoService {

    @Autowired
    private EmpleoRepository empleoRepository;

    
    public EmpleoSugerenciaDTO obtenerEmpleoSugerenciaDTO(int codigoEmpleo) {
        EmpleoSugerenciaDTO empleoSugerenciaDTO = new EmpleoSugerenciaDTO();
        
        Empleos empleo = empleoRepository.findByCodigoEmpleo(codigoEmpleo);
        Empresas empresa = empleo.getCodigoEmpresa();
        empleoSugerenciaDTO = new EmpleoSugerenciaDTO(empleo, empresa);
        
        return empleoSugerenciaDTO;
    }

    public List<EmpleoSugerenciaDTO> obtenerEmpleosExceptoDelUsuario(int codigoUsuario) {
        List<Empleos> empleos = empleoRepository.findAllExceptByUsuario(codigoUsuario);
    
        return empleos.stream()
            .map(empleo -> new EmpleoSugerenciaDTO(empleo, empleo.getCodigoEmpresa()))
            .collect(Collectors.toList());   
    }
    
}
