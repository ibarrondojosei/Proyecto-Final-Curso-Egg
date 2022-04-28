package com.example.sportplanesentrenamiento.servicios;

import com.example.sportplanesentrenamiento.entidades.Anamnesis;

import java.util.List;

public interface AnamnesisServiceI {

    public void guardar(Anamnesis anamnesis);

    public List<Anamnesis> lista();

    public Anamnesis buscar(String id);

    public void eliminar(Anamnesis anamnesis);

}
