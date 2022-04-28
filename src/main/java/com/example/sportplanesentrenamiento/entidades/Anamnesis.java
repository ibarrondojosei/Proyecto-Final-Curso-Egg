package com.example.sportplanesentrenamiento.entidades;

import com.example.sportplanesentrenamiento.enumeraciones.Alimentacion;
import com.example.sportplanesentrenamiento.enumeraciones.AntecedentesFamiliares;
import com.example.sportplanesentrenamiento.enumeraciones.SiNo;
import com.example.sportplanesentrenamiento.enumeraciones.Suenio;
import java.io.Serializable;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Anamnesis implements Serializable{

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    private Double peso;

    private Double talla;

    private Double imc; //peso / talla*2

    @Enumerated(EnumType.STRING)
    private Suenio suenio;

    private int cantHorasSuenio;

    private String ActividadFisicaHoras;

    @Enumerated(EnumType.STRING)
    private Alimentacion alimentacion;
    
    private SiNo nutricion;
    private String ObsNutricion;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<AntecedentesFamiliares> antecedentesFamiliares;

    @Enumerated(EnumType.STRING)
    private SiNo accidentes;
    private String ObsAccidentes;

    @Enumerated(EnumType.STRING)
    private SiNo operaciones;
    private String ObsOperaciones;

    @Enumerated(EnumType.STRING)
    private SiNo enfermedades;
    private String ObsEnfermedades;

    @Enumerated(EnumType.STRING)
    private SiNo limitaciones;
    private String ObsLimitaciones;

    @Enumerated(EnumType.STRING)
    private SiNo alergias;
    private String ObsAlergias;

    @Enumerated(EnumType.STRING)
    private SiNo medicamentos;
    private String ObsMedicamentos;

}
