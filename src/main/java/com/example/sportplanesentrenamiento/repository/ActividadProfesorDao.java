
package com.example.sportplanesentrenamiento.repository;

import com.example.sportplanesentrenamiento.entidades.ActividadProfesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActividadProfesorDao extends JpaRepository<ActividadProfesor, String> {
    
}
