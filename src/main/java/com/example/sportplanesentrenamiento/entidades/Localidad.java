package com.example.sportplanesentrenamiento.entidades;

import java.io.Serializable;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Localidad implements Serializable{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid",strategy = "uuid2")
    private String id;
    
    private String nombre;

    private Integer codigo_postal;
    
    private Integer provincia_id;
}
