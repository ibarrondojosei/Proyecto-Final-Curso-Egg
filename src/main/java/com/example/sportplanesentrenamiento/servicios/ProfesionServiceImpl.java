
package com.example.sportplanesentrenamiento.servicios;

import com.example.sportplanesentrenamiento.entidades.Profesion;
import com.example.sportplanesentrenamiento.repository.ProfesionDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfesionServiceImpl implements ProfesionServiceI{

    @Autowired
    private ProfesionDao profesionDao;
    
    @Override
    public void guardar(Profesion profesion) {
        profesionDao.save(profesion);
    }

    @Override
    public List<Profesion> lista() {
        return profesionDao.findAll();
    }

    @Override
    public Profesion buscar(String Id) {
        return profesionDao.findById(Id).orElse(null);
    }

    @Override
    public void eliminar(Profesion profesion) {
        profesionDao.delete(profesion);
    }
    
}
