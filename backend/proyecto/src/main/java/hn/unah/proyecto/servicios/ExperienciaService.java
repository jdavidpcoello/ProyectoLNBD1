package hn.unah.proyecto.servicios;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.dto.ExperienciasDTO;
import hn.unah.proyecto.dto.NewExperienceDTO;
import hn.unah.proyecto.entidades.Empresas;
import hn.unah.proyecto.entidades.Experiencias;
import hn.unah.proyecto.entidades.TipoEmpleos;
import hn.unah.proyecto.entidades.TipoLugarTrabajo;
import hn.unah.proyecto.entidades.Usuarios;
import hn.unah.proyecto.repositorios.EmpresaRepository;
import hn.unah.proyecto.repositorios.ExperienciaRepository;
import hn.unah.proyecto.repositorios.TipoEmpleoRepository;
import hn.unah.proyecto.repositorios.TipoLugarTrabajoRepository;
import hn.unah.proyecto.repositorios.UsuariosRepository;

@Service
public class ExperienciaService {


    @Autowired
    private ExperienciaRepository experienciaRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private TipoEmpleoRepository tipoEmpleoRepository;

    @Autowired
    TipoLugarTrabajoRepository tipoLugarTrabajoRepository;

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

    public ExperienciasDTO nuevaExperienciasUsuario(NewExperienceDTO nvoRegistro){
        Experiencias experienciaBd = new Experiencias();

        Usuarios usuario = usuariosRepository.findById(nvoRegistro.getCodigoUsuario()).get();
        experienciaBd.setUsuario(usuario);
        
        Empresas empresa = empresaRepository.findByNombreEmpresas(nvoRegistro.getEmpresa());
        experienciaBd.setEmpresa(empresa);

        experienciaBd.setCargo(nvoRegistro.getCargo());
        experienciaBd.setMesInicio(nvoRegistro.getMesInicioJob());
        experienciaBd.setAnioInicio(nvoRegistro.getAnioInicioJob());
        experienciaBd.setAnioFinal(nvoRegistro.getAnioFinalJob());
        experienciaBd.setMesFinal(nvoRegistro.getMesFinalJob());

        TipoEmpleos tipoEmpleo = tipoEmpleoRepository.findByTipoEmpleo(nvoRegistro.getTipoEmpleo());
        experienciaBd.setTipoEmpleo(tipoEmpleo);

        TipoLugarTrabajo tipoLugarTrabajo = tipoLugarTrabajoRepository.findByTipoLugarTrabajo(nvoRegistro.getTipoLugarTrabajo());
        experienciaBd.setTipoLugarTrabajo(tipoLugarTrabajo);

        experienciaBd.setDescripcion(nvoRegistro.getJobDescription());

        experienciaRepository.save(experienciaBd);

        ExperienciasDTO experienciasDTO = new ExperienciasDTO();
        experienciasDTO.setAnioFinal(experienciaBd.getAnioFinal());
        experienciasDTO.setAnioInicio(experienciaBd.getAnioInicio());
        experienciaBd.setCargo(experienciaBd.getCargo());
        experienciaBd.setCodigoExperiencia(experienciaBd.getCodigoExperiencia());
        experienciaBd.setDescripcion(experienciaBd.getDescripcion());
        experienciaBd.setEmpresa(empresa);
        experienciaBd.setMesFinal(experienciaBd.getMesFinal());
        experienciaBd.setMesInicio(experienciaBd.getMesInicio());
        experienciaBd.setTipoEmpleo(tipoEmpleo);
        experienciaBd.setTipoLugarTrabajo(tipoLugarTrabajo);

        return experienciasDTO;
    }
}
