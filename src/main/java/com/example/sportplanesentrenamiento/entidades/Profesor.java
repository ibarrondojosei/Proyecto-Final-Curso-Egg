package com.example.sportplanesentrenamiento.entidades;

import com.example.sportplanesentrenamiento.entidades.ActividadProfesor;
import com.example.sportplanesentrenamiento.enumeraciones.ProfesionEnum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import static org.hibernate.engine.internal.Cascade.cascade;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.ModelAttribute;


@Entity
@Data
@Table(name = "profesor")
public class Profesor implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private String fotoPerfil;

    @NotEmpty(message = "Este campo no puede ser nulo o estar vacio")
    private String nombre;

    @NotEmpty(message = "Este campo No debe estar vacio")
    private String apellido;

    @NotNull(message = "Error el dni no debe estar vacio")
    private String dni;

    @NotNull(message = "Error el telefono no debe estar vacio")
    private Long telefono;

    @NotNull(message = "Debe ingresar una fecha de nacimiento")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;

    @NotEmpty(message = "Este campo no puede ser nulo o estar vacio")
    @Email
    private String email;

    @NotEmpty(message = "Este campo no puede ser nulo o estar vacio")
    private String direccion;

    @OneToOne
    private Localidad localidad;

    @OneToOne
    private Provincia provincia;

    @ManyToOne()
    private Alumno alumno;

    @NotNull(message = "Debe seleccionar una Profesion")
    @Enumerated(EnumType.STRING)
    private ProfesionEnum profesionEnum;

//    @ManyToMany(cascade = CascadeType.MERGE)
//    @JoinTable(name="profesor_actividades", joinColumns=@JoinColumn(name="profesor_id", referencedColumnName="id"),
//                                  inverseJoinColumns=@JoinColumn(name="actividades_id", referencedColumnName="id"))
    @Valid
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE})
    private ActividadProfesor actividadProfesor;

    private String descripcionActividad;

    @OneToMany(cascade = CascadeType.MERGE)
    private List<Valoracion> valoracion;

    private double promedio;
    
    @ElementCollection
    private List<String> fotos;

    private Boolean alta;

    @NotEmpty(message = "Este campo no puede ser nulo o estar vacio")
    @Size(min = 6, message = "La contraseña no debe ser menor a 6")
    private String password;

    @NotEmpty(message = "Este campo no puede ser nulo o estar vacio")
    @Size(min = 6, message = "La contraseña no debe ser menor a 6")
    private String password2;

}
