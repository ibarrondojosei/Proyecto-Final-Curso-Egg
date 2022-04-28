package com.example.sportplanesentrenamiento.controladores;

import com.example.sportplanesentrenamiento.entidades.*;
import com.example.sportplanesentrenamiento.enumeraciones.ActividadesEnum;
import com.example.sportplanesentrenamiento.enumeraciones.ProfesionEnum;
import com.example.sportplanesentrenamiento.errores.ErrorService;

import com.example.sportplanesentrenamiento.repository.ProfesorDao;
import com.example.sportplanesentrenamiento.servicios.*;
import java.io.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.mail.Multipart;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@RequestMapping("/profesor")
public class ProfesorController {

    @Autowired
    private ProfesorServiceImpl profesorServiceImpl;

    @Autowired
    private ProfesorDao profesorDao;

    @Autowired
    private AlumnoServiceI alumnoService;

    @Autowired
    private ValoracionServiceI valoracionService;

    @Autowired
    private LocalidadServiceI localidadServiceI;

    @Autowired
    private ProvinciaServiceI provinciaServiceI;

    @Autowired
    private ActividadProfesorServiceI actividadProfesorService;

    @GetMapping("/profesores-list")
    public String listarLibros(Model model) {
        List<Profesor> listadoDeProfesores = profesorServiceImpl.listarProfesores();

        model.addAttribute("titulo", "Lista de profesores");
        model.addAttribute("profesores", listadoDeProfesores);

        return "profesor/listarProfesores";
    }

    @GetMapping("/ingresar-profesor")
    public String crearProfesor(Model model, @RequestParam(required = false) String accion) {
        System.out.println("\n entro a profesor/ingresar-profesor *****\n");

        if (accion == null) {
            accion = "Registrarme";
        }

        List<Alumno> listaDeAlumnos = alumnoService.lista();
        List<Provincia> provincias = provinciaServiceI.listaOrdenadaPorNombre();
        List<Localidad> localidades = localidadServiceI.listaOrdenadaPorNombre();
        List<ActividadProfesor> actividadesProfesor = actividadProfesorService.listarActividadProfesores();

        Profesor profesor = new Profesor();

        model.addAttribute("profesor", profesor);
        model.addAttribute("alumnos", listaDeAlumnos);
        model.addAttribute("provincias", provincias);
        model.addAttribute("localidades", localidades);

        model.addAttribute("profesionesEnum", ProfesionEnum.values());

        model.addAttribute("actividades", ActividadesEnum.values());

        model.addAttribute("actividadesProfesor", actividadesProfesor);

        model.addAttribute("accion", accion);

        return "profesor/RegistroProfesor";
    }

    @PostMapping("/guardar-profesor")
    public String guardarProfesor2(@Valid @ModelAttribute Profesor profesor, Errors result,
            @RequestParam(required = false, name = "file") MultipartFile foto,
            @RequestParam(required = false, name = "files") MultipartFile files[],
            @RequestParam(required = false) String accion, Model model, RedirectAttributes attribute) {

        System.out.println("\n entro a guardar-profesor2 \n");
//
        for (int i = 0; i < files.length; i++) {
            System.out.println("\ntamano de files : " + files.length);
            System.out.println("files : " + files[i] + "\n");
        }

        List<Provincia> provincias = provinciaServiceI.listaOrdenadaPorNombre();
        List<Localidad> localidades = localidadServiceI.listaOrdenadaPorNombre();
        List<ActividadProfesor> actividades = actividadProfesorService.listarActividadProfesores();
        ArrayList lista = new ArrayList();

        try {
            List<Alumno> listaDeAlumnos = alumnoService.lista();

            if (accion == null) {
                accion = "Registrarme";
            }
            System.out.println("\n antes de la trabajar la fotoPerfil \n");
            // tratamiento de la foto en alumno
            if (!foto.isEmpty()) { //si la foto no viene vacia q la guarde
//                if (foto.getContentType().endsWith("jpg")) {
                    String ruta = "C://Temp//uploads//";
//                String ruta = "C://EGG//ProyectoFinalEgg//uploads"; // ruta relativa donde voy a guardar la foto
                    // nombre con uuid para que no se repita nunca el nmbre de la foto convertida a String
                    String nombreFoto = UUID.randomUUID().toString() + "-" + foto.getOriginalFilename();
                    Path path = Paths.get(ruta); // path donde
                    // consulta si existe la Directorio sino la crea
                    if (!Files.exists(path)) {
                        File directorio = new File("C://Temp//uploads//");
//                    File directorio = new File("C://EGG//ProyectoFinalEgg//uploads");
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
                        profesor.setFotoPerfil(nombreFoto);
                    } catch (Exception ex) {
                        System.err.println(ex);
                    }
//                }
            }

            //--------------------for each para recorrrer la foto ----------------
//            for (MultipartFile ar : listaFotos) {
            for (int i = 0; i < files.length; i++) {
//                if (files[i].getContentType().endsWith("jpg")) {
//                    System.out.println("\nentro al for de lista de fotos");
//                    System.out.println("\ntamano de files : " + files.length);
//                    System.out.println("files : " + files[i] + "\n");
                    MultipartFile file = files[i];
//                if (!ar.isEmpty()) { //si la foto no viene vacia q la guarde
                    String ruta = "C://Temp//uploads//"; // ruta relativa donde voy a guardar la foto
                    // nombre con uuid para que no se repita nunca el nmbre de la foto convertida a String
                    String nombreFoto = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
                    Path path = Paths.get(ruta); // path donde
                    // consulta si existe la Directorio sino la crea
                    if (!Files.exists(path)) {
                        File directorio = new File("C://Temp//uploads//");
                        if (directorio.mkdirs()) {
                            System.out.println("Directorio creado");
                        } else {
                            System.out.println("Error al crear directorio");
                        }
                    }

                    try {
                        byte[] bytes = file.getBytes(); //alamceno los bytes de la foto
                        Path rutaAbsoluta = Paths.get(ruta + "//" + nombreFoto);//ruta abs concatenada con el nombre de la foto
                        //  averiguar que hace esta linea
                        Files.write(rutaAbsoluta, bytes);

                        //profesor.setFotoPerfil(nombreFoto);
//                        FotosProfesor fotos = new FotosProfesor();
//                        fotos.setFotos(nombreFoto);
//                        System.out.println("fotos : " + fotos);
                        System.out.println("nombreFoto : " + nombreFoto);
                        System.out.println("Profesor antes de la lista de fotos : " + profesor);
                        lista.add(nombreFoto);
//                    profesor.setFotos(lista);
                        System.out.println("Profesor + lista de fotos : " + profesor);
                    } catch (Exception ex) {
                        System.err.println(ex);
                    }
//                }

            }
            profesor.setFotos(lista);
//            System.out.println("\n LA FOTO ANTES DEL ERRORS: " + profesor.getFotoPerfil() + "\n");
            profesor.setAlta(true);

            if (result.hasErrors()) {
                model.addAttribute("titulo", "Formulario: Nuevo Profesor");
                model.addAttribute("profesor", profesor);
                model.addAttribute("provincias", provincias);
                model.addAttribute("localidades", localidades);
                model.addAttribute("actividades", ActividadesEnum.values());
                model.addAttribute("profesionesEnum", ProfesionEnum.values());
//                System.out.println("Hubieron algunos errores al cargar el PROFESOR");
                model.addAttribute("accion", accion);
//                System.out.println("PROFESOR CON ERROR: " + profesor.toString());
//                System.out.println("\n EL HAS ERRORS DICE: " + result.getAllErrors().toString() + "\n");
                return "profesor/RegistroProfesor";
            }

//            System.out.println("Profesor Ingresado con sus datos antes de Persistir: " + profesor.toString());
//            System.out.println("EL ID DEL PROFESOR PERSISTIDO ES: " + profesor.getId());
            System.out.println("profesor antes de guardar con foto Perfirl :" + profesor + "\n");
            profesorServiceImpl.guardarProfesor(profesor);
            return "redirect:/";

        } catch (ErrorService es) {
            model.addAttribute("error", es.getMessage());
            model.addAttribute("provincias", provincias);
            model.addAttribute("localidades", localidades);
            model.addAttribute("actividades", ActividadesEnum.values());
            model.addAttribute("profesionesEnum", ProfesionEnum.values());
            model.addAttribute("accion", accion);
            return "profesor/RegistroProfesor";
        }

    }

    @PreAuthorize("hasAnyRole('ROLE_PROFESOR_REGISTRADO')")
    @GetMapping("/editar-profesor/{id}")
    public String editarProfesor(HttpSession session, @PathVariable("id") String id, @RequestParam(required = false) String accion, Model model, RedirectAttributes attribute) throws ErrorService {
        System.out.println("\n entro a profesor/editar-profesor \n");
        Profesor profesor = null;

        if (accion == null) {
            accion = "Editar Perfil";
        }

        if (!id.equalsIgnoreCase("0") || id != null) {
            profesor = profesorServiceImpl.buscarProfesorPorId(id);

            Profesor login = (Profesor) session.getAttribute("profesorsession");

            if (profesor == null || !profesor.getId().equals(login.getId())) {
                return "redirect:/index";
            }

//            if (profesor == null) {
//                attribute.addFlashAttribute("error", "Atencion!, el ID del profesor no existe!");
//                return "redirect:/profesor/index";
//            }
        } else {
//            System.out.println("Error con el ID del profesor");
            attribute.addFlashAttribute("error", "ERROR! con el ID del Profesor!");

            return "redirect:/profesor/profesores-list";
        }

        List<Alumno> listaDeAlumnos = alumnoService.lista();
        List<Valoracion> listaDeValoraciones = valoracionService.listarValoraciones_1();
        List<Provincia> provincias = provinciaServiceI.listarProvincias();
        List<Localidad> localidades = localidadServiceI.listarLocalidades();

        model.addAttribute("titulo", "Formulario: Editar Profesor");
        model.addAttribute("profesor", profesor);
        model.addAttribute("alumnos", listaDeAlumnos);
        model.addAttribute("actividades", ActividadesEnum.values());
        model.addAttribute("profesionesEnum", ProfesionEnum.values());
        model.addAttribute("valoraciones", listaDeValoraciones);
        model.addAttribute("provincias", provincias);
        model.addAttribute("localidades", localidades);
        model.addAttribute("accion", accion);
        return "profesor/RegistroProfesor";
    }

    @GetMapping("/eliminar-profesor/{id}")
    public String eliminarProfesor(@PathVariable("id") String id, RedirectAttributes attribute) {

        Profesor profesor = null;

        if (!id.equalsIgnoreCase("0")) {
            profesor = profesorServiceImpl.buscarProfesorPorId(id);

            if (profesor == null) {
                attribute.addFlashAttribute("error", "Atencion!, el ID del profesor no existe!");
                return "redirect:/profesor/profesor-list";
            }

        } else {
            System.out.println("Error con el ID del profesor");
            attribute.addFlashAttribute("error", "Atencion!, el ID del profesor no existe!");
            return "redirect:/profesor/profesores-list";
        }

        profesorServiceImpl.eliminarProfesor(id);
        attribute.addFlashAttribute("warning", "Atencion! El profesor ha sido eliminado con exito.");
        System.out.println("Profesor eliminado con exito");
        return "redirect:/profesor/profesores-list";
    }

    @GetMapping("/alumnos-vinculados/{idProfesor}")
    public String listaDeAlumnosProfesor(@PathVariable("idProfesor") String idProfesor, Model model) {

        Profesor profesor = profesorServiceImpl.buscarProfesorPorId(idProfesor);

        model.addAttribute("titulo", "Lista de Alumnos del Profesor " + profesor.getNombre());
        model.addAttribute("alumnosVinculados", alumnoService.findAllByProfesorId(idProfesor));
        List<Alumno> alumnosDelProfesor = alumnoService.findAllByProfesorId(idProfesor);

//        model.addAttribute("profesor",profesorService.listarProfesores());
        return "profesor/MisAlumnosProfesor";
    }

    @PreAuthorize("hasAnyRole('ROLE_PROFESOR_REGISTRADO')")
    @GetMapping("/perfil-menu/{id}")
    public String perfilMenu(@PathVariable("id") String idProfesor, Model model, HttpSession session) {

        Profesor profesor = profesorServiceImpl.buscarProfesorPorId(idProfesor);
        Profesor login = (Profesor) session.getAttribute("profesorsession");

        if (idProfesor == null || !idProfesor.equals(login.getId())) {

            return "redirect:/index";
        }

        session.setAttribute("profesorsession", profesor);
        model.addAttribute("profesor", profesor);

        return "profesor/Profesor";
    }

//    @PreAuthorize("hasAnyRole('ROLE_PROFESOR_REGISTRADO')")
//    @GetMapping("/perfil-prof/{id}")
//    public String perfilProfesor(HttpSession session, @PathVariable("id") String id, Model model) {
//
//        Profesor profesor = null;
//
//        if (id != null) {
//            Profesor login = (Profesor) session.getAttribute("profesorsession");
//            profesor = profesorService.buscarProfesorPorId(id);
//
//
//            if (profesor == null || !profesor.getId().equals(login.getId())) {
//                return "redirect:/index";
//            }
//            model.addAttribute("profesor", profesor);
////            model.addAttribute("fotosPerfil",profesorService.FindAllByFotoId(id));
//        }
//
//        return "profesor/PerfilProfesor";
//    }
    /**
     * metodo que muestra el perfil del profesor
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/perfil-prof/{id}")
    public String buscar(HttpSession session, @PathVariable("id") String id, Model model) {
        System.out.println("\n entro a profesor/buscar *************\n\n");

        Profesor profesor = profesorServiceImpl.buscarProfesorPorId(id);

        if (profesorServiceImpl.buscarProfesorPorId(id) == null) {
            System.out.println("\n profesor nulo \n");
            model.addAttribute("error", "Error al Buscar por Id");
            return "redirect:/profesor/listar";
        }

        System.out.println("\n encontro al profesor : " + profesor);

//        model.addAttribute("actividades", profesorServiceImpl.listaActividadPorProfesor(id));
        model.addAttribute("profesor", profesor);

        return "profesor/perfilProfesor";

    }

////    @PreAuthorize("hasAnyRole('ROLE_PROFESOR_REGISTRADO')")
//    @GetMapping("/perfil-prof/{id}")
//    public String perfilProfesor2( @PathVariable("id") String id, Model model) {
//        System.out.println(" \n entro a profesor/perfil-prof");
//        Profesor profesor = null;
//        System.out.println("id_profesor : " + id);
//        
//        if (id != null) {
//            Profesor login = (Profesor) session.getAttribute("profesorsession");
//            
//            profesor = profesorServiceImpl.buscarProfesorPorId(id);
//
//            if (profesor == null || !profesor.getId().equals(login.getId())) {
//                return "redirect:/index";
//            }
//
//            model.addAttribute("profesor", profesor);
////            model.addAttribute("fotosPerfil",profesorService.FindAllByFotoId(id));
//        }
//
//        return "profesor/PerfilProfesor";
//    }
    @GetMapping("/perfil-alum/{id}")
    public String alumnoResumido(@PathVariable(name = "id") String id, Model model) {

        Alumno alumno = alumnoService.buscarAlumno(id);

        model.addAttribute("alumno", alumno);

        return "profesor/PerfilAlumno";
    }

    /*
    @PostMapping("/guardar-profesor")
    public String guardarProfesor2(@Valid @ModelAttribute Profesor profesor, Errors result, @RequestParam(required = false, name = "fotoPerfil") MultipartFile foto, @RequestParam(required = false) String accion, Model model, RedirectAttributes attribute) {

        List<Provincia> provincias = provinciaService.listaOrdenadaPorNombre();
        List<Localidad> localidades = localidadService.listaOrdenadaPorNombre();
        List<ActividadProfesor> actividades = actividadProfesorService.listarActividadProfesores();


        System.out.println("\n IMAGEN RECIBIDA POR PARAMETRO: " + foto.toString());

        if (foto.isEmpty()) {
            System.out.println("\n ESTO SIGNIFICA QUE NO TIENE NINGUNA FOTO!!!!!!! \n");
        }


//        try {
        System.out.println("\nID DEL PROFESOR EN POST profesor /guardar-profesor: " + profesor.getId() + "\n");
        List<Alumno> listaDeAlumnos = alumnoService.lista();
        List<Valoracion> listaDeValoraciones = valoracionService.listarValoraciones();

        if (accion == null) {
            accion = "Registrarme";
        }


        // tratamiento de la foto en alumno
        if (!foto.isEmpty()) { //si la foto no viene vacia q la guarde
            String ruta = "C://ImagenesProyecto//uploads"; // ruta relativa donde voy a guardar la foto
            // nombre con uuid para que no se repita nunca el nmbre de la foto convertida a String
            String nombreFoto = UUID.randomUUID().toString() + "-" + foto.getName();
            Path path = Paths.get(ruta); // path donde
            // consulta si existe la Directorio sino la crea
            if (!Files.exists(path)) {
                File directorio = new File("C://ImagenesProyecto//uploads");
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
                profesor.setFotoPerfil(nombreFoto);
            } catch (Exception ex) {
                System.err.println(ex);
            }
        }
        System.out.println("\n LA FOTO ANTES DEL ERRORS: " + profesor.getFotoPerfil() + "\n");
        profesor.setAlta(true);

        if (result.hasErrors()) {
            model.addAttribute("titulo", "Formulario: Nuevo Profesor");
            model.addAttribute("profesor", profesor);
            model.addAttribute("provincias", provincias);
            model.addAttribute("localidades", localidades);
            model.addAttribute("actividades", ActividadesEnum.values());
            model.addAttribute("profesionesEnum", ProfesionEnum.values());
            System.out.println("Hubieron algunos errores al cargar el PROFESOR");
            model.addAttribute("accion", accion);
            System.out.println("PROFESOR CON ERROR: " + profesor.toString());
            System.out.println("\n EL HAS ERRORS DICE: "+ result.getAllErrors().toString()+"\n");
            return "profesor/RegistroProfesor";
        }

        System.out.println("Profesor Ingresado con sus datos antes de Persistir: " + profesor.toString());
//        attribute.addFlashAttribute("success","Profesor Guardado con exito!");
        System.out.println("EL ID DEL PROFESOR PERSISTIDO ES: " + profesor.getId());

//            profesorService.guardarProfesor(profesor, imagen);

        profesorService.guardarProfesorSinImagen(profesor);
        return "redirect:/index";
//        } catch (ErrorService es) {
//            model.addAttribute("error", es.getMessage());
//            model.addAttribute("provincias", provincias);
//            model.addAttribute("localidades", localidades);
//            model.addAttribute("actividades", ActividadesEnum.values());
//            model.addAttribute("profesionesEnum", ProfesionEnum.values());
//            model.addAttribute("accion", accion);
//            return "profesor/RegistroProfesor";
//        }

    }

     */
}
