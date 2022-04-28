package com.example.sportplanesentrenamiento.repository;

import com.example.sportplanesentrenamiento.entidades.Localidad;
import java.util.List;
import org.springframework.data.domain.Sort;
;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalidadDao extends JpaRepository<Localidad,String> {
    
    @Query("SELECT l FROM Localidad l ORDER BY l.nombre ASC")
    public List<Localidad> listaOrdenadaPorNombre();        
       
       
}
       
   
