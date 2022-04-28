
package com.example.sportplanesentrenamiento.entidades;

import com.example.sportplanesentrenamiento.entidades.Profesor;
import com.example.sportplanesentrenamiento.enumeraciones.ActividadesEnum;
import java.io.Serializable;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
//@Table(name="actividadProfesor")
public class ActividadProfesor implements Serializable{
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<ActividadesEnum> nombre;  

    @ManyToMany
    private List<Profesor> profesores;
     
}
