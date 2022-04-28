package com.example.sportplanesentrenamiento.repository;

import com.example.sportplanesentrenamiento.entidades.Anamnesis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnamnesisDao extends JpaRepository<Anamnesis, String> {
    
}
