package com.example.sportplanesentrenamiento.servicios;

import com.example.sportplanesentrenamiento.entidades.ActividadProfesor;
import com.example.sportplanesentrenamiento.entidades.Alumno;
import com.example.sportplanesentrenamiento.entidades.Profesor;
import com.example.sportplanesentrenamiento.enumeraciones.ActividadesEnum;
import com.example.sportplanesentrenamiento.errores.ErrorService;
import com.example.sportplanesentrenamiento.repository.AlumnoDao;
import com.example.sportplanesentrenamiento.repository.ProfesorDao;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProfesorServiceImpl implements ProfesorServiceI, UserDetailsService {

    @Autowired
    private ProfesorDao profesorDao;
    
    @Autowired
    private AlumnoDao alumnoDao;

    @Override
    public List<Profesor> listarProfesores() {
        return profesorDao.findAll();
    }

    @Override
    public Profesor buscarProfesorPorId(String id) {
        return profesorDao.findById(id).orElse(null);
    }

//    @Override
//    @Transactional
//    public void guardarProfesor(Profesor profesor, @RequestParam(name = "file", required = false) MultipartFile foto, ArrayList<MultipartFile> listaFotos, Model model) throws ErrorService, IOException {
//        
//        validarContrasenias(profesor.getPassword(), profesor.getPassword2());
//        String clave = profesor.getPassword();
//        String clave2 = profesor.getPassword2();
//        String encriptada = new BCryptPasswordEncoder().encode(clave);
//        String encriptada2 = new BCryptPasswordEncoder().encode(clave2);
//
//        profesor.setPassword(encriptada);
//        profesor.setPassword2(encriptada2);
//
//        if (!foto.isEmpty()) { //si la foto no viene vacia q la guarde
//            String ruta = "C://Users//alexm//Documentos//Ejercicios egg//PFE//ProyectoFinalEgg//uploads";
//            // nombre con uuid para que no se repita nunca el nmbre de la foto convertida a String
//            String nombreFoto = UUID.randomUUID().toString() + "-" + foto.getName();
//            Path path = Paths.get(ruta); // path donde 
//
//            // consulta si existe la Directorio sino la crea
//            if (!Files.exists(path)) {
//                File directorio = new File("C://Users//alexm//Documentos//Ejercicios egg//PFE//ProyectoFinalEgg//uploads");
//                if (directorio.mkdirs()) {
//                    System.out.println("Directorio creado");
//                } else {
//                    System.out.println("Error al crear directorio");
//                }
//            } else {
//                System.out.println("\nel directorio uploads existe \n\n");
//            }
//
//            try {
//                byte[] bytes = foto.getBytes(); //alamceno los bytes de la foto
//                Path rutaAbsoluta = Paths.get(ruta + "//" + nombreFoto);//ruta abs concatenada con el nombre de la foto
//                //  averiguar que hace esta linea
//                Files.write(rutaAbsoluta, bytes);
//                profesor.setFotoPerfil(nombreFoto);
//
//            } catch (Exception ex) {
//                System.err.println(ex);
//            }
//            System.out.println("\n profesor guardado con foto : " + profesor);
//            model.addAttribute("profesor", profesor);
//            profesor.setAlta(true);
//            profesorDao.save(profesor);
//
//        }
//        
//        System.out.println("\n profesor guardado con foto : " + profesor);
//        model.addAttribute("profesor", profesor);
//        profesor.setAlta(true);
//        profesorDao.save(profesor);
//
//    }

//    @Override
//    @Transactional
//    public void guardarProfesor(Profesor profesor, MultipartFile archivo, ArrayList<MultipartFile> listaFotos) throws ErrorService, IOException {
//        validarContrasenias(profesor.getPassword(), profesor.getPassword2());
//        String clave = profesor.getPassword();
//        String clave2 = profesor.getPassword2();
//        String encriptada = new BCryptPasswordEncoder().encode(clave);
//        String encriptada2 = new BCryptPasswordEncoder().encode(clave2);
//
//        profesor.setPassword(encriptada);
//        profesor.setPassword2(encriptada2);
//
//        Foto foto = fotoService.guardar(archivo);
//
//        profesor.setFotoPerfil(foto);
//
//        List<Foto> fotosProf = new ArrayList<>();
//
//        for (MultipartFile ar : listaFotos) {
//            System.out.println("getbyte " + ar.getBytes());
//            System.out.println("getclass " + ar.getClass());
//            System.out.println("getcontentype " + ar.getContentType());
//            System.out.println("getinputstream " + ar.getInputStream());
//            System.out.println("getname " + ar.getName());
//            System.out.println("getoriginalfilename " + ar.getOriginalFilename());
//            System.out.println("getresource " + ar.getResource());
//            System.out.println("getsize " + ar.getSize());
//
//            if (ar.getSize() != 0) {
//
//                Foto fotosLista = new Foto();
//                fotosLista = fotoService.guardar(ar);
//                fotosLista.setProfesor(profesor);
//
//                fotosProf.add(fotosLista);
//            } else {
//                continue;
//            }
//        }
//        
//        foto.setProfesor(profesor);
//        profesor.setFotos(fotosProf);
//        profesor.setAlta(true);
//        profesorDao.save(profesor);
//    }
    
    
    @Override
    public Profesor guardarProfesorSinImagen(Profesor profesor) {
        return profesorDao.save(profesor);
    }

    @Override
    @Transactional
    public void eliminarProfesor(String id) {
        profesorDao.deleteById(id);
    }

    public void validarContrasenias(String password, String password2) throws ErrorService {
        if (password == null || password.isEmpty() || password.length() < 6) {
            throw new ErrorService("La clave del profesor no puede ser nula y tiene que tener al menos 6 digitos");
        }
        if (!password.equals(password2)) { // si la clave 1 nno es igual a la clave 2
            throw new ErrorService("Las claves deben ser iguales");
        }
    }


    //@Override// metodo independiente grax frederic
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        Profesor profesor = profesorDao.buscarPorMail(mail);
        Alumno alumno = alumnoDao.buscarPorMail(mail);

        if (profesor != null) {

            List<GrantedAuthority> permisos = new ArrayList<>();

            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_PROFESOR_REGISTRADO");
            permisos.add(p1);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("profesorsession", profesor);

            User user = new User(profesor.getEmail(), profesor.getPassword(), permisos);
            return user;

        } else if (alumno != null) {
            List<GrantedAuthority> permisos2 = new ArrayList<>();

            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_ALUMNO_REGISTRADO");
            permisos2.add(p1);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("alumnosession", alumno);

            User user = new User(alumno.getEmail(), alumno.getPassword(), permisos2);
            return user;

        } else {
            return null;
        }

    }

//    @Override
//    public List<Profesor> listaPorActividades(String actividad) {
//        return profesorDao.findAllByActividadProfesorNombre(actividad);
//    }

    @Override
    public List<Profesor> listaPorActividadLocalidadProvincia(ActividadesEnum actividad, String localidad, String provincia) {
        return profesorDao.findAllByActividadProfesorNombreAndLocalidadIdAndProvinciaId(actividad, localidad, provincia);
    }

    @Override
    public List<Profesor> listarTodosLosProfesoresDelAlumno(String alumnoId) {
        return profesorDao.findAllByAlumnoId(alumnoId);
    }

    @Override
    public List<Profesor> listaPorActividades(String actividad) {
        return profesorDao.findAllByActividadProfesorNombre(actividad);
    }

    @Override
    @Transactional
    public void guardarProfesor(Profesor profesor) throws ErrorService {
        validarContrasenias(profesor.getPassword(), profesor.getPassword2());
        String clave = profesor.getPassword();
        String clave2 = profesor.getPassword2();
        String encriptada = new BCryptPasswordEncoder().encode(clave);
        String encriptada2 = new BCryptPasswordEncoder().encode(clave2);

        profesor.setPassword(encriptada);
        profesor.setPassword2(encriptada2);

        profesor.setAlta(true);
        profesorDao.save(profesor);
    }


}
