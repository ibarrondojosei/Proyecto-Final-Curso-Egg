
package com.example.sportplanesentrenamiento.servicios;

import com.example.sportplanesentrenamiento.entidades.ActividadProfesor;
import java.util.List;

public interface ActividadProfesorServiceI {
    
    public List<ActividadProfesor> listaActividades();
    
    public List<ActividadProfesor> listarActividadProfesores();

    public ActividadProfesor buscarActividadProfesorPorId(String id);

    public void guardarActividadProfesor(ActividadProfesor actividadProfesor);

    public void eliminarActividadProfesorPorId(String id);
       
}
