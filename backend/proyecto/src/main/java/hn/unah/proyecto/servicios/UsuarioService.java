package hn.unah.proyecto.servicios;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hn.unah.proyecto.dto.ActualizarUsuarioDTO;
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
import hn.unah.proyecto.entidades.SitiosWeb;
import hn.unah.proyecto.entidades.TipoEmpleos;
import hn.unah.proyecto.entidades.TiposSitiosWeb;
import hn.unah.proyecto.entidades.Usuarios;
import hn.unah.proyecto.repositorios.CiudadesRepository;
import hn.unah.proyecto.repositorios.EducacionRepository;
import hn.unah.proyecto.repositorios.EmpresaRepository;
import hn.unah.proyecto.repositorios.ExperienciaRepository;
import hn.unah.proyecto.repositorios.InstitucionesRepository;
import hn.unah.proyecto.repositorios.PaisesRepository;
import hn.unah.proyecto.repositorios.SitioWebRepository;
import hn.unah.proyecto.repositorios.TipoEmpleoRepository;
import hn.unah.proyecto.repositorios.TiposWebRepository;
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
    private ExperienciaRepository experienciasRepository;

    @Autowired
    private EducacionRepository educacionRepository;

    @Autowired
    private TiposWebRepository tiposWebRepository;

    @Autowired
    private SitioWebRepository sitioWebRepository;

    public UsuariosDTO iniciarSesion(String email, String password) {
        Usuarios usuario = usuariosRepository.findByEmail(email);
        if (usuario == null) {
            return null;
        }
        if (!(usuario.getContrasenia().equals(password))) {
            return null;
        }

        UsuariosDTO usuariosDTO = new UsuariosDTO();

        usuariosDTO.setCodigoUsuario(usuario.getCodigoUsuario());
        usuariosDTO.setEmail(usuario.getEmail());
        usuariosDTO.setNombre(usuario.getNombre());
        usuariosDTO.setApellidos(usuario.getApellidos());
        usuariosDTO.setFotoPerfil(usuario.getFotoPerfil());
        usuariosDTO.setFotoPortada(usuario.getFotoPortada());
        usuariosDTO.setSector(usuario.getSector());
        usuariosDTO.setTitular(usuario.getTitular());
        usuariosDTO.setFechaNacimiento(usuario.getFechaNacimiento());
        usuariosDTO.setUrlPerfil(usuario.getUrlPerfil());
        usuariosDTO.setNombreAdicional(usuario.getNombreAdicional());

        Paises pais = paisesRepository.findById(usuario.getPais().getCodigoPais()).get();
        Ciudades ciudad = ciudadesRepository.findById(usuario.getCiudad().getCodigoCiudad()).get();
        Ciudades parentCity;

        PaisesDTO paisDTO = new PaisesDTO();
        paisDTO.setCodigoPais(pais.getCodigoPais());
        paisDTO.setNombre(pais.getNombre());

        CiudadesDTO ciudadDTO = new CiudadesDTO();
        ciudadDTO.setCodigoCiudad(ciudad.getCodigoCiudad());
        ciudadDTO.setNombreCiudad(ciudad.getNombreCiudad());
        ciudadDTO.setPais(paisDTO);

        if (ciudad.getCiudadPadre() != null) {
            parentCity = ciudadesRepository.findById(ciudad.getCiudadPadre().getCodigoCiudad()).get();
        } else {
            parentCity = null;
        }

        ciudadDTO.setCiudadPadre(parentCity);

        usuariosDTO.setCiudad(ciudadDTO);
        usuariosDTO.setPais(paisDTO);

        return usuariosDTO;
    }

    public UsuariosDTO registrarUsuario(NewUserDTO nvoUsuario) {

        UsuariosDTO usuarioDTO = new UsuariosDTO();

        if (usuariosRepository.existsByEmail(nvoUsuario.getEmail())) {
            usuarioDTO = null;
        } else {
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
            if (nvoUsuario.getParentCity().equals("")) {
                ciudad = ciudadesRepository.findByNombreCiudadAndCiudadPadre(nvoUsuario.getCity(), null);
            }
            {
                Ciudades ciudadPadre = ciudadesRepository.findByNombreCiudadAndCiudadPadre(nvoUsuario.getParentCity(),
                        null);
                ciudad = ciudadesRepository.findByNombreCiudadAndCiudadPadre(nvoUsuario.getCity(), ciudadPadre);
            }
            usuarioBd.setCiudad(ciudad);
            usuarioBd.setUrlPerfil("www.linkedin.com/in/" + nvoUsuario.getFirstName().toLowerCase() + "-"
                    + nvoUsuario.getLastName().toLowerCase());

            Educacion educacion = new Educacion();
            Experiencias experiencias = new Experiencias();

            if (nvoUsuario.getJob().equals("")) {
                int dia = Integer.parseInt(nvoUsuario.getBirthDay());
                int mes = Integer.parseInt(nvoUsuario.getBirthMonth());
                int anio = Integer.parseInt(nvoUsuario.getBirthYear());

                LocalDate birthDate = LocalDate.of(anio, mes, dia);

                usuarioBd.setFechaNacimiento(birthDate);
                educacion.setAnioInicio(nvoUsuario.getFirstYear());
                educacion.setAnioFinal(nvoUsuario.getLastYear());

                Instituciones instituciones = institucionesRepository
                        .findByNombreInstitucion(nvoUsuario.getSchoolName());
                educacion.setInstitucionEducativa(instituciones);
            } else {
                experiencias.setCargo(nvoUsuario.getJob());

                TipoEmpleos tipoEmpleo = tipoEmpleoRepository.findByTipoEmpleo(nvoUsuario.getTypeJob());
                experiencias.setTipoEmpleo(tipoEmpleo);

                Empresas empresa = empresaRepository.findByNombreEmpresas(nvoUsuario.getPlaceJob());
                experiencias.setEmpresa(empresa);

            }

            usuariosRepository.save(usuarioBd);

            if (nvoUsuario.getJob().equals("")) {
                educacion.setUsuario(usuarioBd);
                educacionRepository.save(educacion);
            } else {
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

            usuarioDTO.setCodigoUsuario(usuarioBd.getCodigoUsuario());
            usuarioDTO.setEmail(usuarioBd.getEmail());
            usuarioDTO.setNombre(usuarioBd.getNombre());
            usuarioDTO.setFotoPerfil(usuarioBd.getFotoPerfil());
            usuarioDTO.setApellidos(usuarioBd.getApellidos());
            usuarioDTO.setTitular(usuarioBd.getTitular());
            usuarioDTO.setCiudad(ciudadesDTO);
            usuarioDTO.setPais(paisDTO);
            usuarioDTO.setUrlPerfil(usuarioBd.getUrlPerfil());
        }
        return usuarioDTO;
    }

    public Usuarios obtenerUsuarioPorId(int codigoUsuario) {
        return usuariosRepository.findById(codigoUsuario).orElse(null);
    }

    public UsuariosDTO cambiarFotoPerfil(int codigoUsuario, String fotoPerfil) {
        Usuarios usuario = usuariosRepository.findById(codigoUsuario).get();
        usuario.setFotoPerfil(fotoPerfil);

        usuariosRepository.save(usuario);

        UsuariosDTO usuariosDTO = new UsuariosDTO();

        usuariosDTO.setCodigoUsuario(usuario.getCodigoUsuario());
        usuariosDTO.setEmail(usuario.getEmail());
        usuariosDTO.setNombre(usuario.getNombre());
        usuariosDTO.setApellidos(usuario.getApellidos());
        usuariosDTO.setFotoPerfil(usuario.getFotoPerfil());
        usuariosDTO.setFotoPortada(usuario.getFotoPortada());
        usuariosDTO.setSector(usuario.getSector());
        usuariosDTO.setTitular(usuario.getTitular());
        usuariosDTO.setFechaNacimiento(usuario.getFechaNacimiento());
        usuariosDTO.setUrlPerfil(usuario.getUrlPerfil());
        usuariosDTO.setNombreAdicional(usuario.getNombreAdicional());

        Paises pais = paisesRepository.findById(usuario.getPais().getCodigoPais()).get();
        Ciudades ciudad = ciudadesRepository.findById(usuario.getCiudad().getCodigoCiudad()).get();
        Ciudades parentCity;

        PaisesDTO paisDTO = new PaisesDTO();
        paisDTO.setCodigoPais(pais.getCodigoPais());
        paisDTO.setNombre(pais.getNombre());

        CiudadesDTO ciudadDTO = new CiudadesDTO();
        ciudadDTO.setCodigoCiudad(ciudad.getCodigoCiudad());
        ciudadDTO.setNombreCiudad(ciudad.getNombreCiudad());
        ciudadDTO.setPais(paisDTO);

        if (ciudad.getCiudadPadre() != null) {
            parentCity = ciudadesRepository.findById(ciudad.getCiudadPadre().getCodigoCiudad()).get();
        } else {
            parentCity = null;
        }

        ciudadDTO.setCiudadPadre(parentCity);

        usuariosDTO.setCiudad(ciudadDTO);
        usuariosDTO.setPais(paisDTO);

        return usuariosDTO;
    }

    public UsuariosDTO cambiarFotoPortada(int codigoUsuario, String fotoPortada) {
        Usuarios usuario = usuariosRepository.findById(codigoUsuario).get();
        usuario.setFotoPortada(fotoPortada);

        usuariosRepository.save(usuario);

        UsuariosDTO usuariosDTO = new UsuariosDTO();

        usuariosDTO.setCodigoUsuario(usuario.getCodigoUsuario());
        usuariosDTO.setEmail(usuario.getEmail());
        usuariosDTO.setNombre(usuario.getNombre());
        usuariosDTO.setApellidos(usuario.getApellidos());
        usuariosDTO.setFotoPerfil(usuario.getFotoPerfil());
        usuariosDTO.setFotoPortada(usuario.getFotoPortada());
        usuariosDTO.setSector(usuario.getSector());
        usuariosDTO.setTitular(usuario.getTitular());
        usuariosDTO.setFechaNacimiento(usuario.getFechaNacimiento());
        usuariosDTO.setUrlPerfil(usuario.getUrlPerfil());
        usuariosDTO.setNombreAdicional(usuario.getNombreAdicional());

        Paises pais = paisesRepository.findById(usuario.getPais().getCodigoPais()).get();
        Ciudades ciudad = ciudadesRepository.findById(usuario.getCiudad().getCodigoCiudad()).get();
        Ciudades parentCity;

        PaisesDTO paisDTO = new PaisesDTO();
        paisDTO.setCodigoPais(pais.getCodigoPais());
        paisDTO.setNombre(pais.getNombre());

        CiudadesDTO ciudadDTO = new CiudadesDTO();
        ciudadDTO.setCodigoCiudad(ciudad.getCodigoCiudad());
        ciudadDTO.setNombreCiudad(ciudad.getNombreCiudad());
        ciudadDTO.setPais(paisDTO);

        if (ciudad.getCiudadPadre() != null) {
            parentCity = ciudadesRepository.findById(ciudad.getCiudadPadre().getCodigoCiudad()).get();
        } else {
            parentCity = null;
        }

        ciudadDTO.setCiudadPadre(parentCity);

        usuariosDTO.setCiudad(ciudadDTO);
        usuariosDTO.setPais(paisDTO);

        return usuariosDTO;
    }

    public UsuariosDTO actualizarUsuario(ActualizarUsuarioDTO usuario) {
        Usuarios usuariobd = usuariosRepository.findById(usuario.getCodigoUsuario()).get();

        usuariobd.setNombre(usuario.getNombre());
        usuariobd.setApellidos(usuario.getApellido());
        usuariobd.setNombreAdicional(usuario.getNombreAdicional());
        usuariobd.setTitular(usuario.getTitular());
        usuariobd.setSector(usuario.getSector());

        Paises pais = paisesRepository.findByNombre(usuario.getPais());

        Ciudades ciudad;
        if (usuario.getCiudadPadre().equals("")) {
            ciudad = ciudadesRepository.findByNombreCiudadAndCiudadPadre(usuario.getCiudad(), null);
        }
        {
            Ciudades ciudadPadre = ciudadesRepository.findByNombreCiudadAndCiudadPadre(usuario.getCiudadPadre(),
                    null);
            ciudad = ciudadesRepository.findByNombreCiudadAndCiudadPadre(usuario.getCiudad(), ciudadPadre);
        }

        if (usuario.getEnlace() != "") {
            SitiosWeb sitiosWeb = new SitiosWeb();
            sitiosWeb.setUrl(usuario.getEnlace());
            sitiosWeb.setUsuario(usuariobd);
            sitiosWeb.setTextoEnlace(usuario.getTextoEnlace());

            TiposSitiosWeb tiposSitiosWeb = tiposWebRepository.findBySitioWeb(usuario.getTipoWeb());
            sitiosWeb.setTiposSitioWeb(tiposSitiosWeb);
            sitioWebRepository.save(sitiosWeb);
        }

        usuariosRepository.save(usuariobd);

        UsuariosDTO usuariosDTO = new UsuariosDTO();

        usuariosDTO.setCodigoUsuario(usuariobd.getCodigoUsuario());
        usuariosDTO.setEmail(usuariobd.getEmail());
        usuariosDTO.setNombre(usuariobd.getNombre());
        usuariosDTO.setApellidos(usuariobd.getApellidos());
        usuariosDTO.setFotoPerfil(usuariobd.getFotoPerfil());
        usuariosDTO.setFotoPortada(usuariobd.getFotoPortada());
        usuariosDTO.setSector(usuariobd.getSector());
        usuariosDTO.setTitular(usuariobd.getTitular());
        usuariosDTO.setFechaNacimiento(usuariobd.getFechaNacimiento());
        usuariosDTO.setUrlPerfil(usuariobd.getUrlPerfil());
        usuariosDTO.setNombreAdicional(usuariobd.getNombreAdicional());

        PaisesDTO paisDTO = new PaisesDTO();
        paisDTO.setCodigoPais(pais.getCodigoPais());
        paisDTO.setNombre(pais.getNombre());

        CiudadesDTO ciudadDTO = new CiudadesDTO();
        ciudadDTO.setCodigoCiudad(ciudad.getCodigoCiudad());
        ciudadDTO.setNombreCiudad(ciudad.getNombreCiudad());
        ciudadDTO.setPais(paisDTO);

        Ciudades parentCity;
        if (ciudad.getCiudadPadre() != null) {
            parentCity = ciudadesRepository.findById(ciudad.getCiudadPadre().getCodigoCiudad()).get();
        } else {
            parentCity = null;
        }

        ciudadDTO.setCiudadPadre(parentCity);

        usuariosDTO.setCiudad(ciudadDTO);
        usuariosDTO.setPais(paisDTO);

        return usuariosDTO;

    }
}