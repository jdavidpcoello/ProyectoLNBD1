package hn.unah.proyecto.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.dto.EducacionDTO;
import hn.unah.proyecto.entidades.Educacion;
import hn.unah.proyecto.entidades.Instituciones;
import hn.unah.proyecto.entidades.Usuarios;
import hn.unah.proyecto.repositorios.EducacionRepository;
import hn.unah.proyecto.repositorios.InstitucionesRepository;
import hn.unah.proyecto.repositorios.UsuariosRepository;

@Service
public class EducacionService {

    @Autowired
    private EducacionRepository educacionRepository;

    @Autowired
    private InstitucionesRepository institucionesRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    public List <EducacionDTO> educacionUsuario(int codigoUsuario){
       List <Educacion> educationList = educacionRepository.findAllByUsuarioCodigoUsuario(codigoUsuario);

       List <EducacionDTO> educationListDto = new ArrayList<>();

       Usuarios usuario = usuariosRepository.findById(codigoUsuario).get();

       if(!educationList.isEmpty()){
            for (Educacion educacion : educationList) {
                EducacionDTO educacionDTO = new EducacionDTO();
                educacionDTO.setCodigoEducacion(educacion.getCodigoEducacion());
                educacionDTO.setTitulo(educacion.getTitulo());
                educacion.setUsuario(usuario);
                
                Instituciones instiucion = institucionesRepository.findById(educacion.getCodigoEducacion()).get();

                educacionDTO.setInstitucionEducativa(instiucion);
                educacionDTO.setAnioInicio(educacion.getAnioInicio());
                educacionDTO.setAnioFinal(educacion.getAnioFinal());
                educacionDTO.setDisciplinaAcademica(educacion.getDisciplinaAcademica());
                educacionDTO.setNota(educacion.getNota());
                educacionDTO.setActividadesGrupos(educacion.getActividadesGrupos());
                educacionDTO.setAnioFinal(educacion.getAnioFinal());
                educationListDto.add(educacionDTO);
        }
    }
    else{
        educationListDto.add(null);
    }
    return educationListDto;
    }
}
