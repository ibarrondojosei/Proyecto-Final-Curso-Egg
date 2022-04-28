package com.example.sportplanesentrenamiento.servicios;

import com.example.sportplanesentrenamiento.entidades.Localidad;

import java.util.List;
import org.springframework.data.domain.Sort;

public interface LocalidadServiceI {

    public List<Localidad> listarLocalidades();
    
    public Localidad buscarLocalidadPorId(String id);

    public void guardarLocalidad(Localidad localidad);

    public void borrarLocalidadPorId(String id);
    
    public List<Localidad> listaOrdenadaPorNombre();

}
