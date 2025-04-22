package hn.unah.proyecto.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.dto.ExperienciasDTO;
import hn.unah.proyecto.entidades.Experiencias;
import hn.unah.proyecto.repositorios.ExperienciaRepository;

@Service
public class ExperienciaService {


    @Autowired
    private ExperienciaRepository experienciaRepository;

    public List<ExperienciasDTO> trabajoUsuario(int codigoUsuario) {
        List <Experiencias> listJobs = experienciaRepository.findAllByUsuarioCodigoUsuario(codigoUsuario);
        List <ExperienciasDTO> listJobsDtos = new ArrayList<>();

        if(!listJobs.isEmpty()){
            for (Experiencias experiencias : listJobs) {
                ExperienciasDTO experienciasDTO = new ExperienciasDTO();
                experienciasDTO.setCargo(experiencias.getCargo());
                experienciasDTO.setDescripcion(experiencias.getDescripcion());
                experienciasDTO.setAnioInicio(experiencias.getAnioInicio());
                experienciasDTO.setAnioFinal(experiencias.getAnioFinal());
                experienciasDTO.setCodigoExperiencia(experiencias.getCodigoExperiencia());
                experienciasDTO.setMesInicio(experiencias.getMesInicio());
                experienciasDTO.setMesFinal(experiencias.getMesFinal());
                experienciasDTO.setTipoEmpleos(experiencias.getTipoEmpleo());
                experienciasDTO.setEmpresas(experiencias.getEmpresa());
                listJobsDtos.add(experienciasDTO);
            }
        }else{
            listJobsDtos.add(null);
        }
        return listJobsDtos;
    }
}
