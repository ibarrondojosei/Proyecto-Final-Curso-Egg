package com.example.sportplanesentrenamiento.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
public class Alumno implements Serializable{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String foto;

    private String nombre;

    private String apellido;

    private String dni;

    private Long telefono;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    private String email;

    private String direccion;

    @OneToOne
    private Localidad localidad;

    @OneToOne
    private Provincia provincia;
    
    @Valid
    @OneToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE})
    private Anamnesis anamnesis;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Profesor> profesor;

//    @OneToMany
//    private List<FeedBack> feedBack;

    private Boolean alta;

    private String password;

    private String password2;
    
}
