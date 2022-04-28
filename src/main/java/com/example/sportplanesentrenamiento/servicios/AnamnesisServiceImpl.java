package com.example.sportplanesentrenamiento.servicios;

import com.example.sportplanesentrenamiento.entidades.Alumno;
import com.example.sportplanesentrenamiento.entidades.Anamnesis;
import com.example.sportplanesentrenamiento.errores.ErrorService;
import com.example.sportplanesentrenamiento.repository.AnamnesisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnamnesisServiceImpl implements AnamnesisServiceI{
    @Autowired
    private AnamnesisDao anamnesisDao;

    @Override
    public void guardar(Anamnesis anamnesis) {
        anamnesisDao.save(anamnesis);
    }

    @Override
    public List<Anamnesis> lista() {
        return anamnesisDao.findAll();
    }

    @Override
    public Anamnesis buscar(String id) {
        return anamnesisDao.getById(id);
    }

    @Override
    public void eliminar(Anamnesis anamnesis) {

    }
    
    public Alumno validacionAnamnesis(Alumno alumno) throws ErrorService {
        Anamnesis anamnesis = alumno.getAnamnesis();
        if (anamnesis.getPeso() == null) {
            throw new ErrorService(" El Peso no puede estar vacío");
        }
        if (anamnesis.getTalla() == null) {
            throw new ErrorService(" La Talla no puede estar vacía");
        }
        if (anamnesis.getActividadFisicaHoras().isEmpty()) {
            throw new ErrorService(" La Horas de activ. Fisica no puede estar vacío");
        }
        if (anamnesis.getAlimentacion() == null) {
            throw new ErrorService(" La Alimentacion no puede estar vacía");
        }
        
        return alumno;   
    }
}
