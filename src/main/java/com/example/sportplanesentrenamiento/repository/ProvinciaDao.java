
package com.example.sportplanesentrenamiento.repository;

import com.example.sportplanesentrenamiento.entidades.Provincia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface ProvinciaDao extends JpaRepository<Provincia, String>{
    
    @Query("SELECT p FROM Provincia p ORDER BY p.nombre ASC")
    public List<Provincia> listaOrdenadaPorNombre(); 
}
