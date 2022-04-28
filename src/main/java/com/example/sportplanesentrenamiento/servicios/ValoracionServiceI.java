package com.example.sportplanesentrenamiento.servicios;

import com.example.sportplanesentrenamiento.entidades.Valoracion;

import java.util.List;

public interface ValoracionServiceI {
    

    public List<Integer> listarValoraciones(String profesorId);
    
    public List<Valoracion> listarValoraciones_1();

    public Valoracion buscarValoracionPorId(String id);

    public void guardarValoracion(Valoracion valoracion);

    public void borrarValoracionPorId(String id);
    
    

}
