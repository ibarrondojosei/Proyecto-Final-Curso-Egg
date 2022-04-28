/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sportplanesentrenamiento.servicios;

import com.example.sportplanesentrenamiento.entidades.ActividadProfesor;
import com.example.sportplanesentrenamiento.repository.ActividadProfesorDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActividadProfesorServiceImpl implements ActividadProfesorServiceI{

    @Autowired
    private ActividadProfesorDao actividadProfesorDao;
    
    @Override
    public List<ActividadProfesor> listaActividades() {
        return actividadProfesorDao.findAll();
    }
    
    @Override
    public List<ActividadProfesor> listarActividadProfesores() {
        return actividadProfesorDao.findAll();
    }

    @Override
    public ActividadProfesor buscarActividadProfesorPorId(String id) {
        return actividadProfesorDao.findById(id).orElse(null);
    }

    @Override
    public void guardarActividadProfesor(ActividadProfesor actividadProfesor) {
        actividadProfesorDao.save(actividadProfesor);
    }

    @Override
    public void eliminarActividadProfesorPorId(String id) {
        actividadProfesorDao.deleteById(id);
    }

        
}
