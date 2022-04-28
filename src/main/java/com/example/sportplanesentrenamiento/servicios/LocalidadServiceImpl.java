package com.example.sportplanesentrenamiento.servicios;

import com.example.sportplanesentrenamiento.entidades.Localidad;
import com.example.sportplanesentrenamiento.repository.LocalidadDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class LocalidadServiceImpl implements LocalidadServiceI {

    @Autowired
    private LocalidadDao localidadDao;

    @Override
    public List<Localidad> listarLocalidades() {
        return localidadDao.findAll();
    }

    @Override
    public Localidad buscarLocalidadPorId(String id) {
        return localidadDao.findById(id).orElse(null);
    }

    @Override
    public void guardarLocalidad(Localidad localidad) {
        localidadDao.save(localidad);
    }

    @Override
    public void borrarLocalidadPorId(String id) {
        localidadDao.deleteById(id);
    }

    @Override
    public List<Localidad> listaOrdenadaPorNombre() {
        return localidadDao.listaOrdenadaPorNombre();
    }

   
}
