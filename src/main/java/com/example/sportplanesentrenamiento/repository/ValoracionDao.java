package com.example.sportplanesentrenamiento.repository;

import com.example.sportplanesentrenamiento.entidades.Valoracion;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ValoracionDao extends JpaRepository<Valoracion,String> {
    
    @Query("SELECT v.puntaje FROM Valoracion v WHERE v.profesor.id = :query ")
    public List<Integer> listaValoracion(@Param("query") String profesorId);
       
}
