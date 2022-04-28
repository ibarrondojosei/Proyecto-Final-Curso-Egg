

package com.example.sportplanesentrenamiento.repository;

import com.example.sportplanesentrenamiento.entidades.Profesion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesionDao extends JpaRepository<Profesion, String>{
    
}
