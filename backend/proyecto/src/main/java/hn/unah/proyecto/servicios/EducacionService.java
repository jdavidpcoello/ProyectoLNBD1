package hn.unah.proyecto.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.dto.EducacionDTO;
import hn.unah.proyecto.dto.NewEducacionDTO;
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
    private UsuariosRepository usuariosRepository;

    @Autowired
    private InstitucionesRepository institucionesRepository;

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

    public EducacionDTO nuevoEducacionUsuario(NewEducacionDTO nvoRegistro){
        Educacion educacionBd = new Educacion();

        Usuarios usuario = usuariosRepository.findById(nvoRegistro.getCodigoUsuario()).get();
        
        educacionBd.setUsuario(usuario);
        educacionBd.setTitulo(nvoRegistro.getTitulo());
        educacionBd.setDisciplinaAcademica(nvoRegistro.getDisciplinaAcademica());
        educacionBd.setAnioInicio(nvoRegistro.getAnioInicio());
        educacionBd.setAnioFinal(nvoRegistro.getAnioFinal());
        educacionBd.setMesInicio(nvoRegistro.getMesInicio());
        educacionBd.setMesFinal(nvoRegistro.getMesFinal());
        educacionBd.setNota(nvoRegistro.getNota());
        educacionBd.setDescripcion(nvoRegistro.getDescripcion());
        educacionBd.setActividadesGrupos(nvoRegistro.getActividadesGrupos());
        
        Instituciones institucion = institucionesRepository.findByNombreInstitucion(nvoRegistro.getInstitucionEducativa());
        educacionBd.setInstitucionEducativa(institucion);

        educacionRepository.save(educacionBd);

        EducacionDTO educacionDTO = new EducacionDTO();
        educacionDTO.setTitulo(educacionBd.getTitulo());
        educacionDTO.setDisciplinaAcademica(educacionBd.getDisciplinaAcademica());
        educacionDTO.setAnioInicio(educacionBd.getAnioInicio());
        educacionDTO.setAnioFinal(educacionBd.getAnioFinal());
        educacionDTO.setMesInicio(educacionBd.getMesInicio());
        educacionDTO.setMesFinal(educacionBd.getMesFinal());
        educacionDTO.setNota(educacionBd.getNota());
        educacionDTO.setActividadesGrupos(educacionBd.getActividadesGrupos());
        educacionDTO.setInstitucionEducativa(institucion);
        educacionDTO.setCodigoEducacion(educacionBd.getCodigoEducacion());
        

        return educacionDTO;
    }
}
