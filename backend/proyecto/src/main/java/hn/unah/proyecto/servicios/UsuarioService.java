package hn.unah.proyecto.servicios;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.dto.CiudadesDTO;
import hn.unah.proyecto.dto.NewUserDTO;
import hn.unah.proyecto.dto.PaisesDTO;
import hn.unah.proyecto.dto.UsuariosDTO;
import hn.unah.proyecto.entidades.Ciudades;
import hn.unah.proyecto.entidades.Educacion;
import hn.unah.proyecto.entidades.Empresas;
import hn.unah.proyecto.entidades.Experiencias;
import hn.unah.proyecto.entidades.Instituciones;
import hn.unah.proyecto.entidades.Paises;
import hn.unah.proyecto.entidades.TipoEmpleos;
import hn.unah.proyecto.entidades.Usuarios;
import hn.unah.proyecto.repositorios.CiudadesRepository;
import hn.unah.proyecto.repositorios.EducacionRepository;
import hn.unah.proyecto.repositorios.EmpresaRepository;
import hn.unah.proyecto.repositorios.ExperienciasRepository;
import hn.unah.proyecto.repositorios.InstitucionesRepository;
import hn.unah.proyecto.repositorios.PaisesRepository;
import hn.unah.proyecto.repositorios.TipoEmpleoRepository;
import hn.unah.proyecto.repositorios.UsuariosRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private PaisesRepository paisesRepository;

    @Autowired
    private CiudadesRepository ciudadesRepository;

    @Autowired
    private InstitucionesRepository institucionesRepository;

    @Autowired
    private TipoEmpleoRepository tipoEmpleoRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    @Autowired
    private ExperienciasRepository experienciasRepository;

    @Autowired
    private EducacionRepository educacionRepository;

    public boolean iniciarSesion(String email,String password) {
        Usuarios usuario = usuariosRepository.findByEmail(email);
        if(usuario==null){
            return false;
        }
        if(!(usuario.getContrasenia().equals(password))){
            return false;
        }
        return true;
    }

    public UsuariosDTO registrarUsuario(NewUserDTO nvoUsuario){
        Usuarios usuarioBd = new Usuarios();
        usuarioBd.setEmail(nvoUsuario.getEmail());
        usuarioBd.setContrasenia(nvoUsuario.getPassword());
        usuarioBd.setNombre(nvoUsuario.getFirstName());
        usuarioBd.setApellidos(nvoUsuario.getLastName());
        usuarioBd.setFotoPerfil(nvoUsuario.getProfilePhoto());
        usuarioBd.setTitular(nvoUsuario.getTitular());
        
        Paises pais = paisesRepository.findByNombre(nvoUsuario.getCountry());
        usuarioBd.setPais(pais);

        Ciudades ciudad;
        if(nvoUsuario.getParentCity().equals("")){
            ciudad = ciudadesRepository.findByNombreCiudadAndCiudadPadre(nvoUsuario.getCity(), null);
        }{
            Ciudades ciudadPadre = ciudadesRepository.findByNombreCiudadAndCiudadPadre(nvoUsuario.getParentCity(), null);
            ciudad = ciudadesRepository.findByNombreCiudadAndCiudadPadre(nvoUsuario.getCity(), ciudadPadre);
        }
        usuarioBd.setCiudad(ciudad);
        usuarioBd.setUrlPerfil("www.linkedin.com/in/"+nvoUsuario.getFirstName()+"-"+nvoUsuario.getLastName());        

        Educacion educacion = new Educacion();
        Experiencias experiencias = new Experiencias();

        if(nvoUsuario.getJob().equals("")){
            int dia = Integer.parseInt(nvoUsuario.getBirthDay());
            int mes = Integer.parseInt(nvoUsuario.getBirthMonth());
            int anio = Integer.parseInt(nvoUsuario.getBirthYear());

            LocalDate birthDate = LocalDate.of(anio, mes, dia);

            usuarioBd.setFechaNacimiento(birthDate);
            educacion.setAnioInicio(nvoUsuario.getFirtYear());
            educacion.setAnioFinal(nvoUsuario.getLastYear());
            
            Instituciones instituciones = institucionesRepository.findByNombreInstitucion(nvoUsuario.getSchoolName());
            educacion.setInstitucionEducativa(instituciones);
        }
        else {
            experiencias.setCargo(nvoUsuario.getJob());
            
            TipoEmpleos tipoEmpleo = tipoEmpleoRepository.findByTipoEmpleo(nvoUsuario.getTypeJob());
            experiencias.setTipoEmpleo(tipoEmpleo);
            
            Empresas empresa = empresaRepository.findByNombreEmpresas(nvoUsuario.getPlaceJob());
            experiencias.setEmpresa(empresa);

        }

        usuariosRepository.save(usuarioBd);

        if(nvoUsuario.getJob().equals("")){
            educacion.setUsuario(usuarioBd);
            educacionRepository.save(educacion);
        }else{
            experiencias.setUsuario(usuarioBd);
            experienciasRepository.save(experiencias);
        }


        PaisesDTO paisDTO = new PaisesDTO();

        paisDTO.setCodigoPais(pais.getCodigoPais());
        paisDTO.setNombre(pais.getNombre());

        CiudadesDTO ciudadesDTO = new CiudadesDTO();

        ciudadesDTO.setCodigoCiudad(ciudad.getCodigoCiudad());
        ciudadesDTO.setNombreCiudad(ciudad.getNombreCiudad());
        ciudadesDTO.setCiudadPadre(ciudad.getCiudadPadre());
        ciudadesDTO.setPais(paisDTO);

        UsuariosDTO usuarioDTO = new UsuariosDTO();
        usuarioDTO.setCodigoUsuario(usuarioBd.getCodigoUsuario());
        usuarioDTO.setEmail(usuarioBd.getEmail());
        usuarioDTO.setContrasenia(usuarioBd.getContrasenia());
        usuarioDTO.setNombre(usuarioBd.getNombre());
        usuarioDTO.setFotoPerfil(usuarioBd.getFotoPerfil());
        usuarioDTO.setApellidos(usuarioBd.getApellidos());
        usuarioDTO.setTitular(usuarioBd.getTitular());
        usuarioDTO.setCiudad(ciudadesDTO);
        usuarioDTO.setPais(paisDTO);

        return usuarioDTO;
    }

    public Usuarios obtenerUsuarioPorId(int codigoUsuario) {
        return usuariosRepository.findById(codigoUsuario).orElse(null);
    }
}
