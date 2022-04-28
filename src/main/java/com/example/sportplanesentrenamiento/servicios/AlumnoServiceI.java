
package com.example.sportplanesentrenamiento.servicios;

import com.example.sportplanesentrenamiento.entidades.Alumno;
import com.example.sportplanesentrenamiento.entidades.Profesor;
import com.example.sportplanesentrenamiento.errores.ErrorService;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface AlumnoServiceI{
    
    public Alumno guardar(Alumno alumno)throws ErrorService;
    
//    public void guardar(Alumno alumno);
    
    public List<Alumno> lista();
    
    public Alumno buscarAlumno(String id);
    
    public void eliminar(String id);
    
     public List<Alumno> findAllByProfesorId(String alumnoId);
     
//      public List<Profesor> listarProfesoresAlumnos(String alumnoId);
     
}
