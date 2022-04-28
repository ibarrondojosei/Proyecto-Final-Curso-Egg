package com.example.sportplanesentrenamiento.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Profesion implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

//    @NotEmpty(message = "Debe ingresar el nombre de su titulo")
//    @Size(min = 5,max = 50,message = "Este campo debe tener como minimo 5 caracteres o 50 caracteres de maximo")
    private String tituloProfesional;

    private Boolean matricula;
    
//    @NotEmpty(message = "Ingrese su numero de matricula")
//    @Size(min = 8,message = "La matricula debe tener al menos 8 caracteres")
    private String numeroMatricula;
    
}
