package com.example.sportplanesentrenamiento.servicios;

import com.example.sportplanesentrenamiento.entidades.Profesion;
import java.util.List;

public interface ProfesionServiceI {

    public void guardar(Profesion profesion);

    public List<Profesion> lista();

    public Profesion buscar(String Id);

    public void eliminar(Profesion profesion);

}
