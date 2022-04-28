
package com.example.sportplanesentrenamiento.servicios;

import com.example.sportplanesentrenamiento.entidades.Alumno;
import com.example.sportplanesentrenamiento.entidades.Profesor;
import com.example.sportplanesentrenamiento.errores.ErrorService;
import com.example.sportplanesentrenamiento.repository.AlumnoDao;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class AlumnoServiceImpl implements AlumnoServiceI{

    @Autowired
    private AlumnoDao alumnoDao;
    
    @Autowired
    private AnamnesisServiceImpl anamnesisServiceImpl;
    
    @Override
    @Transactional
    public Alumno guardar(Alumno alumno) throws ErrorService{
        /*
        antes de guardar alumno, vamos a validar entidad alumno, luego
        validamos anamnesis y nos devuelve alumno con o sin excepciones
        */
        validacionAlumno(alumno);
        
        Alumno alumno1 = anamnesisServiceImpl.validacionAnamnesis(alumno);
        
        return alumnoDao.save(alumno1);
    }

    @Override
    @Transactional
    public List<Alumno> lista() {
        return alumnoDao.findAll();
    }

//    @Override
//    @Transactional
    public Alumno buscaralumno1(String id) {
        return alumnoDao.findById(id).orElse(null);
    }
    
    

    @Override
    @Transactional
    public void eliminar(String id) {
        alumnoDao.deleteById(id);
    }

    public Alumno validacionAlumno(Alumno alumno) throws ErrorService {
        if (alumno.getNombre().isEmpty() || alumno.getNombre() == null || alumno.getNombre().length() < 3) {
            throw new ErrorService(" El nombre no puede estar vacío o tener menos de 3 caracteres");
        }
        if (alumno.getApellido().isEmpty() || alumno.getApellido() == null) {
            throw new ErrorService(" El apellido no puede estar vacío");
        }
        if (alumno.getDni().isEmpty()) {
            throw new ErrorService(" El dni no puede estar vacío");
        }
        if (alumno.getTelefono() == null) {
            throw new ErrorService(" El telefono no puede estar vacío");
        }
        if (alumno.getFechaNacimiento() == null) {
            throw new ErrorService(" La Fecha de Nacimiento no puede estar vacío");
        }
        if(alumno.getLocalidad() == null){
            throw new ErrorService(" La Localidad no puede estar vacío");
        }
        if(alumno.getProvincia() == null){
            throw new ErrorService(" La Provincia no puede estar vacío");
        }
        if (alumno.getEmail().isEmpty() || alumno.getEmail() == null) {
            throw new ErrorService(" El Email no puede estar vacío");
        }
        if (alumno.getPassword().isEmpty() || alumno.getPassword() == null ) {
            throw new ErrorService(" El password no puede estar vacío o no puede ser inferior a 6 carcateres");
        }
        if (alumno.getPassword2().isEmpty() || alumno.getPassword2() == null ) {
            throw new ErrorService(" El password2 no puede estar vacío o no puede ser inferior a 6 caracteres");
        } 
        if (!alumno.getPassword().equals(alumno.getPassword2())) {     
             throw new ErrorService(" NO coinciden los password");
        }
        
        return alumno; 
    }

    @Override
    public List<Alumno> findAllByProfesorId(String profesorId) {
       return alumnoDao.findAllByProfesorId(profesorId);
    }

//    @Override
//    public List<Profesor> listarProfesoresAlumnos(String alumnoId) {
//        return alumnoDao.buscarProfesorAlumno(alumnoId);
//    }

    @Override
    public Alumno buscarAlumno(String id) {
        return alumnoDao.buscarAlumno(id);
    }
    
}
