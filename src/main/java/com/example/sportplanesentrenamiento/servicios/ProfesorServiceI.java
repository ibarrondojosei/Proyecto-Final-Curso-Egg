package com.example.sportplanesentrenamiento.servicios;

import com.example.sportplanesentrenamiento.entidades.ActividadProfesor;
import com.example.sportplanesentrenamiento.entidades.Profesor;
import com.example.sportplanesentrenamiento.enumeraciones.ActividadesEnum;
import com.example.sportplanesentrenamiento.errores.ErrorService;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

public interface ProfesorServiceI {
    //L

    public List<Profesor> listarProfesores();

    public Profesor buscarProfesorPorId(String id);
       
    public void guardarProfesor(Profesor profesor) throws ErrorService;

    public Profesor guardarProfesorSinImagen(Profesor profesor);
       
    public void eliminarProfesor(String id);
    
    
    // metodos que agrego ale
    
    public List<Profesor> listaPorActividades(String actividad);
    
    public List<Profesor> listaPorActividadLocalidadProvincia(ActividadesEnum actividad, String localidad, String provincia);  

    public List<Profesor> listarTodosLosProfesoresDelAlumno(String alumnoId); 
    
    
//    public List<Profesor> findAllByActividadProfesorNombre(ActividadesEnum actividad);

//    public List<ActividadProfesor> listaActividadPorProfesor(String profesorId);
    
}
