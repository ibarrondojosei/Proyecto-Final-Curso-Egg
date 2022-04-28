package com.example.sportplanesentrenamiento.controladores;

import com.example.sportplanesentrenamiento.entidades.Alumno;
import com.example.sportplanesentrenamiento.entidades.Localidad;
import com.example.sportplanesentrenamiento.entidades.Profesor;
import com.example.sportplanesentrenamiento.entidades.Provincia;
import com.example.sportplanesentrenamiento.enumeraciones.ActividadesEnum;
import com.example.sportplanesentrenamiento.enumeraciones.ProfesionEnum;
import com.example.sportplanesentrenamiento.servicios.ActividadProfesorServiceI;
import com.example.sportplanesentrenamiento.servicios.AlumnoServiceImpl;
import com.example.sportplanesentrenamiento.servicios.LocalidadServiceImpl;
import com.example.sportplanesentrenamiento.servicios.ProfesorServiceImpl;
import com.example.sportplanesentrenamiento.servicios.ProvinciaServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/actividad")
public class ActividadController {

    @Autowired
    private ProfesorServiceImpl profesorServiceImpl;

    @Autowired
    private AlumnoServiceImpl alumnoServiceImpl;

    @Autowired
    private ActividadProfesorServiceI actividadProfesorServiceI;

    @Autowired
    private LocalidadServiceImpl localidadServiceImpl;

    @Autowired
    private ProvinciaServiceImpl provinciaServiceImpl;

     /**
     * listado de todos los profesores de la aplicacion
     *
     * @param alumnoId
     * @param model
     * @return
     */
    @GetMapping("/profesor/{alumnoId}")
    public String listaProfesores(@PathVariable("alumnoId") String alumnoId, Model model) {
        System.out.println("\n entro a actividad/profesor/{alumnoId} --------");

        Alumno alumno = alumnoServiceImpl.buscarAlumno(alumnoId);

        model.addAttribute("titulo", "Lista de Profesores");

//        List<Profesor> lista = profesorServiceImpl.listarProfesores();
//        model.addAttribute("listaProfesor", lista);

        List<Localidad> localidades = localidadServiceImpl.listaOrdenadaPorNombre();
        model.addAttribute("localidades", localidades);

        List<Provincia> provincias = provinciaServiceImpl.listaOrdenadaPorNombre();
        model.addAttribute("provincias", provincias);

//        model.addAttribute("actividades", actividadProfesorServiceI.listaActividades());

        model.addAttribute("actividades", ActividadesEnum.values());

        model.addAttribute("EnumProfesion", ProfesionEnum.values());

        model.addAttribute("profesor", new Profesor());
        model.addAttribute("alumno", alumno);

        return "buscadorProfesor";
    }

    /**
     * listado de profesores con los respectivos filtros, Loc, Prov, Actividad
     *
     * @param alumnoId
     * @param localidad
     * @param actividad
     * @param provincia
     * @param model
     * @return
     */
    @GetMapping("/actividad/{alumnoId}")
    public String listaDeProfesoresXActividad(@PathVariable("alumnoId") String alumnoId, @RequestParam(required = false) String localidad, @RequestParam(required = false) ActividadesEnum actividad, @RequestParam(required = false) String provincia, Model model) {
        System.out.println("\n entro a actividad/actividad/{alumnoId}  --------\n");
        System.out.println("actividad : " + actividad);
        System.out.println("localidad : " + localidad);
        System.out.println("provincia : " + provincia);

        List<Profesor> listaProfesor = profesorServiceImpl.listaPorActividadLocalidadProvincia(actividad, localidad, provincia);
        model.addAttribute("listaProfesor", listaProfesor);

        System.out.println("\nLista de profesores filtrados por act.loc.prov.\n");
      
        for (Profesor profesor : listaProfesor) {
            System.out.println("profesor ID : " + profesor.getId());
            
        }

        model.addAttribute("actividades", actividadProfesorServiceI.listaActividades());
        System.out.println("actividades : " + actividadProfesorServiceI.listaActividades());

        model.addAttribute("EnumProfesion", ProfesionEnum.values());

        Alumno alumno = alumnoServiceImpl.buscarAlumno(alumnoId);
        model.addAttribute("alumno", alumno);

        return "resultadoBusqueda";
    }

}
