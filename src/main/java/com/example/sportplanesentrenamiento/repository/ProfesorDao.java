package com.example.sportplanesentrenamiento.repository;

import com.example.sportplanesentrenamiento.entidades.ActividadProfesor;
import com.example.sportplanesentrenamiento.entidades.Profesor;
import com.example.sportplanesentrenamiento.enumeraciones.ActividadesEnum;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorDao extends JpaRepository<Profesor,String> {

    public List<Profesor> findAllByActividadProfesorNombre(String actividad);
    
    public List<Profesor> findAllByActividadProfesorNombreAndLocalidadIdAndProvinciaId(ActividadesEnum actividad, String localidad, String provincia);
    
    public List<Profesor> findAllByAlumnoId(String alumnoId);
    
    @Query("SELECT p FROM Profesor p WHERE p.email = :email")
    public Profesor buscarPorMail(@Param("email") String email);
    
//    @Query("SELECT p FROM Profesor p WHERE p.actividad = ")
//    @Query("SELECT a.nombre FROM Profesor.ActividadProfesor a WHERE Profesor.id = :profesorId")
//    public List<Actividad>
    
   
//    public List<ActividadProfesor> findAllByProfesorId(String profesorId);
   
}
