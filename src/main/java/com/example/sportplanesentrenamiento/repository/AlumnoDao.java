package com.example.sportplanesentrenamiento.repository;

import com.example.sportplanesentrenamiento.entidades.Alumno;
import com.example.sportplanesentrenamiento.entidades.Profesor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoDao extends JpaRepository<Alumno, String> {

    public List<Alumno> findAllByProfesorId(String profesorId);

    @Query("SELECT p FROM Alumno p WHERE p.email = :email")
    public Alumno buscarPorMail(@Param("email") String email);

//    @Query("SELECT a.profesor FROM Alumno a WHERE a.id = :alumnoId")
//    public List<Profesor> buscarProfesorAlumno(@Param("alumnoId") String alumnoId);
    
    @Query("SELECT a FROM Alumno a WHERE a.id = :id")
    public Alumno buscarAlumno(@Param("id") String id);
    
}
