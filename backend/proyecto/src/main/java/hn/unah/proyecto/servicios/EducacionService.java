package hn.unah.proyecto.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.dto.EducacionDTO;
import hn.unah.proyecto.entidades.Educacion;
import hn.unah.proyecto.entidades.Usuarios;
import hn.unah.proyecto.repositorios.EducacionRepository;
import hn.unah.proyecto.repositorios.UsuariosRepository;

@Service
public class EducacionService {

    @Autowired
    private EducacionRepository educacionRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    public List <EducacionDTO> educacionUsuario(int codigoUsuario){
       List <Educacion> educationList = educacionRepository.findAllByUsuarioCodigoUsuario(codigoUsuario);

       List <EducacionDTO> educationListDto = new ArrayList<>();

       if(!educationList.isEmpty()){
            for (Educacion educacion : educationList) {
                EducacionDTO educacionDTO = new EducacionDTO();
                educacionDTO.setCodigoEducacion(educacion.getCodigoEducacion());
                educacionDTO.setTitulo(educacion.getTitulo());
            
                educacionDTO.setInstitucionEducativa(educacion.getInstitucionEducativa());
                educacionDTO.setAnioInicio(educacion.getAnioInicio());
                educacionDTO.setAnioFinal(educacion.getAnioFinal());
                educacionDTO.setDisciplinaAcademica(educacion.getDisciplinaAcademica());
                educacionDTO.setNota(educacion.getNota());
                educacionDTO.setActividadesGrupos(educacion.getActividadesGrupos());
                educationListDto.add(educacionDTO);
            }
        }
        else{
            educationListDto.add(null);
        }
        return educationListDto;
    }

    public EducacionDTO nuevoEducacionUsuario(EducacionDTO nvoRegistro){
        Educacion educacionBd = new Educacion();

        Usuarios usuario = usuariosRepository.findById(nvoRegistro.getUsuariosDTO().getCodigoUsuario()).get();
        
        educacionBd.setUsuario(usuario);
        educacionBd.setTitulo(nvoRegistro.getTitulo());
        EducacionDTO educacionDTO = new EducacionDTO();

        return educacionDTO;
    }
}
