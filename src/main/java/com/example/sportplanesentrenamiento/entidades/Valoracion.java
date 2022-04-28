package com.example.sportplanesentrenamiento.entidades;

import com.example.sportplanesentrenamiento.enumeraciones.Puntaje;
import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Valoracion implements Serializable{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

//    @Enumerated(EnumType.STRING)
//    private Puntaje puntaje;    
    
    private Integer puntaje;
                                    
//    @NotEmpty(message = "Debe ingresar un comentario")
    private String comentario;

    @ManyToOne
    private Alumno alumno;

    @ManyToOne
    private Profesor profesor;
     
}
