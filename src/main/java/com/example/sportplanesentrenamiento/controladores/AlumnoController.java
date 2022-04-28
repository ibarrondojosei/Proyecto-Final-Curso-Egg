package com.example.sportplanesentrenamiento.controladores;

import com.example.sportplanesentrenamiento.entidades.Alumno;

import com.example.sportplanesentrenamiento.entidades.Localidad;
import com.example.sportplanesentrenamiento.entidades.Profesor;
import com.example.sportplanesentrenamiento.entidades.Provincia;

import com.example.sportplanesentrenamiento.entidades.Valoracion;
import com.example.sportplanesentrenamiento.enumeraciones.Alimentacion;
import com.example.sportplanesentrenamiento.enumeraciones.AntecedentesFamiliares;

import com.example.sportplanesentrenamiento.enumeraciones.HorasSemanales;
import com.example.sportplanesentrenamiento.enumeraciones.SiNo;
import com.example.sportplanesentrenamiento.enumeraciones.Suenio;
import com.example.sportplanesentrenamiento.errores.ErrorService;
import com.example.sportplanesentrenamiento.servicios.AlumnoServiceImpl;
import com.example.sportplanesentrenamiento.servicios.LocalidadServiceImpl;
import com.example.sportplanesentrenamiento.servicios.ProfesorServiceImpl;
import com.example.sportplanesentrenamiento.servicios.ProvinciaServiceImpl;
import com.example.sportplanesentrenamiento.servicios.ValoracionServiceImpl;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/alumno")
public class AlumnoController {

    @Autowired
    private AlumnoServiceImpl alumnoServiceImpl;

    @Autowired
    private LocalidadServiceImpl localidadServiceImpl;

    @Autowired
    private ProvinciaServiceImpl provinciaServiceImpl;

    @Autowired
    private ProfesorServiceImpl profesorServiceImpl;

    @Autowired
    private ValoracionServiceImpl valoracionServiceImpl;

    @GetMapping("/panelControl/{id}")
    public String panelControl(@PathVariable("id") String id, Model model) {
        System.out.println("\n entro a alumno/panelControl *********** \n");
        Alumno alumno = alumnoServiceImpl.buscarAlumno(id);
        model.addAttribute("alumno", alumno);
        return "alumno";
    }

    /**
     * metodo para ingresar todos los datos del perfil del alumno
     *
     * @param model
     * @return
     */
    @GetMapping("/crear")
    public String crear(RedirectAttributes redirectAttributes, Model model) {
        System.out.println("\n entro al alumno/crear -------------------- \n");

        try {
            List<Localidad> localidades = localidadServiceImpl.listaOrdenadaPorNombre();
            model.addAttribute("localidades", localidades);

            List<Provincia> provincias = provinciaServiceImpl.listaOrdenadaPorNombre();
            model.addAttribute("provincias", provincias);

            model.addAttribute("horasSemanales", HorasSemanales.values());
            model.addAttribute("alimentaciones", Alimentacion.values());
            model.addAttribute("suenios", Suenio.values());
            model.addAttribute("antecedentesFamiliares", AntecedentesFamiliares.values());
            model.addAttribute("sino", SiNo.values());
            model.addAttribute("titulo", "Crear Alumno");
            Alumno alumno = new Alumno();
            
            System.out.println("\nAlumno recien creado en en el crear: " + alumno + "\n");
            
// esto es para RECARGAR EN EL FORM los datos ingresados en caso de una exception
            if (model.getAttribute("alumno") != null) {
                alumno = (Alumno) model.getAttribute("alumno");
                return "registracionAlumno";
            }

            model.addAttribute("alumno", alumno);

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "registracionAlumno";
        }
        return "registracionAlumno";
    }

    /**
     * metodo para guardar(save) el perfil del alumno incluyendo foto
     *
     * @param alumno
     * @param foto
     * @param model
     * @return
     * @throws Exception
     */
    @PostMapping("/guardar")
    public String guardar(Alumno alumno, @RequestParam(name = "file", required = false) MultipartFile foto,
           Model model) throws ParseException {

        System.out.println("\n entro a alumno/guardar -------------------\n");
        System.out.println("\n(alumno) apenas ingresa al metodo guardar: " + alumno);

        if (alumno.getAnamnesis().getPeso() != null && alumno.getAnamnesis().getTalla() != null) {
            double talla = alumno.getAnamnesis().getTalla() * alumno.getAnamnesis().getTalla();
            double imc1 = alumno.getAnamnesis().getPeso() / talla;

            DecimalFormat formateador = new DecimalFormat("0.#");
            double imc = imc1;
            imc = formateador.parse(formateador.format(imc)).doubleValue();
            alumno.getAnamnesis().setImc(imc);
            System.out.println("imc : " + imc);
        }
        alumno.setAlta(true);

        try {

            // convertimos un Date en LocalDate para calcular la edad
            //        Date date = new Date();
            if (alumno.getFechaNacimiento() != null) {
                ZoneId defaultZoneId = ZoneId.systemDefault();
                Instant instant = alumno.getFechaNacimiento().toInstant();
                LocalDate fechaNac = instant.atZone(defaultZoneId).toLocalDate();

                // calculamos la edad del alumno
                LocalDate actual = LocalDate.now();
                System.out.println("fechaNac : " + fechaNac);
                System.out.println("Fecha Actual : " + actual);
                Period edad = Period.between(fechaNac, actual);
                System.out.println("edad = " + edad.getYears());
            }

            System.out.println("alumno antes de guardar : " + alumno);

           
            if (!foto.isEmpty()) { //si la foto no viene vacia q la guarde
                String ruta = "C://Temp//uploads//";
                // nombre con uuid para que no se repita nunca el nmbre de la foto convertida a String
                String nombreFoto = UUID.randomUUID().toString() + "-" + foto.getOriginalFilename();
                Path path = Paths.get(ruta); // path donde 

                // consulta si existe la Directorio sino la crea
                if (!Files.exists(path)) {
                    File directorio = new File("C://Temp//uploads//");
                    if (directorio.mkdirs()) {
                        System.out.println("Directorio creado");
                    } else {
                        System.out.println("Error al crear directorio");
                    }
                } else {
                    System.out.println("\nel directorio uploads existe \n\n");
                }

                try {
                    byte[] bytes = foto.getBytes(); //alamceno los bytes de la foto
                    Path rutaAbsoluta = Paths.get(ruta + "//" + nombreFoto);//ruta abs concatenada con el nombre de la foto
                    //  averiguar que hace esta linea
                    Files.write(rutaAbsoluta, bytes);
                    alumno.setFoto(nombreFoto);

                } catch (Exception ex) {
                    System.err.println(ex);
                }

                Alumno alumno1 = new Alumno();
                alumno1 = alumnoServiceImpl.guardar(alumno);

                System.out.println("\nalumno guardado con foto : " + alumno1);

                model.addAttribute("alumno", alumno1);
                return "alumno";

            }
 
            Alumno alumno1 = new Alumno();
            alumno1 = alumnoServiceImpl.guardar(alumno);
            System.out.println("\nalumno guardado sin foto : " + alumno1);

            model.addAttribute("alumno", alumno1);
            model.addAttribute("success", "Error Foto!!!!!");

        } catch (ErrorService e) {

            System.out.println("\n\nalumno catch : " + alumno);
            System.out.println("error : " + e.getMessage());
            
            return "redirect:/alumno/crear";

        }
        return "alumno";
    }

    /**
     * metodo que muestra el perfil del alumno
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/buscar/{id}")
    public String buscar(@PathVariable("id") String id, Model model) {
        System.out.println("\n entro a alumno/buscar *************\n\n");

        if (alumnoServiceImpl.buscarAlumno(id) == null) {
            model.addAttribute("error", "Error al Buscar por Id");
            return "redirect:/alumno/listar";
        }

        model.addAttribute("alumno", alumnoServiceImpl.buscarAlumno(id));
        return "perfilAlumno";
    }

    /**
     * metodo que edita, y modifica los datos del perfil del alumno
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/modificar/{id}")
    public String modificar(@PathVariable("id") String id, Model model) {
        System.out.println("\n entro a alumno/modificar -----------------\n");

        if (alumnoServiceImpl.buscarAlumno(id) == null) {
            model.addAttribute("error", "Error al Modificar");
            return "redirect:/alumno/listar";
        }

        Alumno alumno = alumnoServiceImpl.buscarAlumno(id);

        System.out.println("alumno : " + alumno);
        System.out.println("foto : " + alumno.getFoto());

        List<Localidad> localidades = localidadServiceImpl.listarLocalidades();
        model.addAttribute("localidades", localidades);

        List<Provincia> provincias = provinciaServiceImpl.listarProvincias();
        model.addAttribute("provincias", provincias);

        model.addAttribute("horasSemanales", HorasSemanales.values());
        model.addAttribute("alimentaciones", Alimentacion.values());
        model.addAttribute("suenios", Suenio.values());
        model.addAttribute("antecedentesFamiliares", AntecedentesFamiliares.values());
        model.addAttribute("sino", SiNo.values());

        model.addAttribute("alumno", alumno);

        return "registracionAlumno.html";
    }

    /**
     * metodo para eliminar un alumno
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") String id, Model model) {
        System.out.println("\n entro a alumno/eliminar --------------");
        if (alumnoServiceImpl.buscarAlumno(id) == null) {
            model.addAttribute("error", "Error al eliminar");
            return "redirect:/alumno/listar";
        }
        alumnoServiceImpl.eliminar(id);
        return "eliminadoCorrectamente";
    }

    /**
     * metodo para vincular un profesor a un alumno
     *
     * @param alumnoId
     * @param profesorId
     * @param model
     * @return
     * @throws Exception
     */
//    @GetMapping("/guardarProfesor/{profesorId}/{alumnoId}")
//    public String guardarProfesor(@PathVariable("alumnoId") String alumnoId,
//            @PathVariable("profesorId") String profesorId, Model model) throws Exception {
//        System.out.println("\n entro a alumno/guardarProfesor -------------------\n");
//
//        Alumno alumno = new Alumno();
//        alumno = alumnoServiceImpl.buscarAlumno(alumnoId);
//
//        Profesor profesor = profesorServiceImpl.buscarProfesorPorId(profesorId);
//
//        alumno.getProfesor().add(profesor);
//        profesor.getAlumno().add(alumno);
//
//        alumno.setAlta(true);
//        alumnoServiceImpl.guardar(alumno);
//
//        return "redirect:/mail/enviar";
//    }

    /**
     * metodo que nos muestra el perfil resumido de un profesor a seleccionar
     *
     * @param alumnoId
     * @param profesorId
     * @param model
     * @return
     */
    @GetMapping("/profesorResumido/")
    public String perfilResumidoProfe(@RequestParam(required = false) String alumnoId, @RequestParam(required = false) String profesorId, Model model) {

        System.out.println("\n\nentro a alumno/profesorResumido  -----\n");
        System.out.println("alumnoId : " + alumnoId + "\n");
        System.out.println("profesorId : " + profesorId + "\n");

//        Profesor profesor = profesorServiceImpl.buscarProfesorPorId(profesorId);
//        Alumno alumno = alumnoServiceImpl.buscar(alumnoId);

        model.addAttribute("profesor", profesorServiceImpl.buscarProfesorPorId(profesorId));
        model.addAttribute("alumno", alumnoServiceImpl.buscarAlumno(alumnoId));

        return "perfilResumidoProfesor";
    }

    /**
     * metodo que nos guarda el Profesor seleccionado por el Alumno
     *
     * @param alumnoId
     * @param profesorId
     * @param model
     * @return
     */
    @GetMapping("/profesorSeleccionado/")
    public String profesorSeleccionado(@RequestParam(required = false) String alumnoId, @RequestParam(required = false) String profesorId, Model model) throws ErrorService {
        System.out.println("\n\nentro a alumno/profesorSeleccionado  -----\n");
        
        System.out.println("alumnoId : " + alumnoId + "\n");
        System.out.println("profesorId : " + profesorId + "\n");

//        List<Profesor> profesores = new ArrayList();
        
        Profesor profesor = profesorServiceImpl.buscarProfesorPorId(profesorId);
        
        System.out.println("\npaso a profesor ------\n");
        
        Alumno alumno =  new Alumno();
        alumno = alumnoServiceImpl.buscarAlumno(alumnoId);

        System.out.println("\nAlumno sin Profe : " + alumno + "\n");

        // agrego a la lista de profesores del alumno , un nuevo profesor
//        profesores.add(profesor);
        
        System.out.println("\n antes de setear el alumno/profesor \n");
        
        alumno.getProfesor().add(profesor);

        System.out.println("\nAlumno con Profe : " + alumno + "\n");

        alumnoServiceImpl.guardar(alumno);

        // creamos cuerpo del mail
        String body = "Hola profesor@ " + " " + profesor.getNombre() + " " + profesor.getApellido()
                + ", mi nombre es " + alumno.getNombre() + " " + alumno.getApellido()
                + ", y quisiera contactarme con ud. por un plan de entrenamiento. Muchas Gracias."
                + " Mis datos personales completos son : "
                + " mi Altura es de : " + alumno.getAnamnesis().getTalla() + "cm"
                + " mi peso es : " + alumno.getAnamnesis().getPeso() + "kg"
                + " mi IMC es : " + alumno.getAnamnesis().getImc()
                + " , duermo Habitualmente " + alumno.getAnamnesis().getCantHorasSuenio() + "hs"
                + " y mi descanso es " + alumno.getAnamnesis().getSuenio()
                + " , mi actividad fisica semanal es de " + alumno.getAnamnesis().getActividadFisicaHoras() + "hs, "
                + alumno.getAnamnesis().getNutricion() + " tengo plan de alimentacion,  "
                + alumno.getAnamnesis().getObsNutricion() + ", "
                + alumno.getAnamnesis().getAccidentes() + " he tenido accidentes,  "
                + alumno.getAnamnesis().getObsAccidentes() + ", "
                + alumno.getAnamnesis().getOperaciones() + " he tenido operaciones,  "
                + alumno.getAnamnesis().getObsOperaciones() + ", "
                + alumno.getAnamnesis().getEnfermedades() + " he tenido enfermedades,  "
                + alumno.getAnamnesis().getObsEnfermedades() + ", "
                + alumno.getAnamnesis().getLimitaciones() + " tengo limitaciones,  "
                + alumno.getAnamnesis().getObsLimitaciones() + ", "
                + alumno.getAnamnesis().getAlergias() + " tengo alergias,  "
                + alumno.getAnamnesis().getObsAlergias() + ", "
                + alumno.getAnamnesis().getMedicamentos() + " tomo medicamentos,  "
                + alumno.getAnamnesis().getObsMedicamentos() + ", "
                + " y mis antecedentes familiares relacionado a enfermades son "
                + alumno.getAnamnesis().getAntecedentesFamiliares().toString() + ", "
                + " y mi email es " + alumno.getEmail();

        model.addAttribute("alumno", alumno);
        model.addAttribute("profesor", profesor);
        model.addAttribute("subject", "Contacto de SportPlanesEntrenamiento");
        model.addAttribute("body", body);

        return "enviarMail";
    }

    /**
     * aqui mostramos todos los profesores vinculados con un alumno
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/profesorLista/{id}")
    public String listaProfesoresDelAlumno(@PathVariable("id") String id, Model model) {
        System.out.println("\n entro a alumno/profesorLista --------\n");
        System.out.println("\n id - parametro = " + id);
        List<Profesor> listaProfesor = profesorServiceImpl.listarTodosLosProfesoresDelAlumno(id);
        System.out.println("\nantes del for each **************\n");
        for (Profesor profesor : listaProfesor) {
            System.out.println("profesores = " + profesor.getNombre() + profesor.getApellido());
        }

        System.out.println("\n despues del for each //////////////\n");
        
//        Alumno alumno1 = alumnoServiceImpl.buscarAlumno(id);
        
//        System.out.println("\nalumno1 : " + alumno1);
        
        model.addAttribute("listaProfesor", listaProfesor);
        
       model.addAttribute("alumno", alumnoServiceImpl.buscarAlumno(id));

        System.out.println("alumno despues del model : " + alumnoServiceImpl.buscarAlumno(id));

        return "misProfesoresAlumno";
    }

    /**
     * en este metodo calificamos con * a un profesor
     *
     * @param alumnoId
     * @param profesorId
     * @param model
     * @return
     */
    @GetMapping("/profesorValorar/")
    public String profesorValorar(@RequestParam(required = false) String alumnoId, @RequestParam(required = false) String profesorId, Model model) throws ErrorService {
        System.out.println("\n\nentro a alumno/profesorValorar  -----\n");
        System.out.println("alumnoId : " + alumnoId + "\n");
        System.out.println("profesorId : " + profesorId + "\n");

        Profesor profesor = profesorServiceImpl.buscarProfesorPorId(profesorId);
        Alumno alumno = alumnoServiceImpl.buscarAlumno(alumnoId);

        alumno = alumnoServiceImpl.guardar(alumno);

        Valoracion valoracion = new Valoracion();
        model.addAttribute("valoracion", valoracion);
        model.addAttribute("alumno", alumno);
        model.addAttribute("profesor", profesor);

        return "calificarProfesor";
    }

    /**
     * aqui calculamos el promedio de calificaciones del profesor
     * @param comentario
     * @param estrella
     * @param alumnoId
     * @param profesorId
     * @param model
     * @return
     * @throws ParseException
     */
    @GetMapping("/calificar")
    public String calificar(@RequestParam(name = "comentario", required = false) String comentario, @RequestParam(name = "puntaje", required = false) Integer puntaje, @RequestParam(name = "alumnoId", required = false) String alumnoId, @RequestParam(name = "profesorId", required = false) String profesorId, Model model) throws ParseException {
        System.out.println("\n\nentro a alumno/calificar  -----\n");

        Profesor profesor = profesorServiceImpl.buscarProfesorPorId(profesorId);
        Alumno alumno = alumnoServiceImpl.buscarAlumno(alumnoId);

        Valoracion valoracion = new Valoracion();
        valoracion.setAlumno(alumno);
        valoracion.setProfesor(profesor);
        valoracion.setComentario(comentario);
        valoracion.setPuntaje(puntaje);

        System.out.println("\nvaloracion : " + valoracion + " \n");

        /*
            una vez guardada la valoracion vamos al metodo
            que calcula el promedio y le setea el setPromedio
         */
        valoracionServiceImpl.guardarValoracion(valoracion);
        System.out.println("profesor : " + profesor.getId() + " " + profesor.getNombre());

        valoracionServiceImpl.promedioValoracion(profesor.getId());
        model.addAttribute("alumno", alumno);

        return "alumno";
    }

}
