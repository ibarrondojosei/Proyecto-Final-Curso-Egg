package com.example.sportplanesentrenamiento.controladores;

import com.example.sportplanesentrenamiento.entidades.Alumno;
import com.example.sportplanesentrenamiento.entidades.Profesor;
import com.example.sportplanesentrenamiento.servicios.AlumnoServiceImpl;
import com.example.sportplanesentrenamiento.servicios.ProfesorServiceImpl;
import com.example.sportplanesentrenamiento.servicios.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private ProfesorServiceImpl profesorServiceImpl;

    @Autowired
    private AlumnoServiceImpl alumnoServiceImpl;

    @Autowired
    private MailServiceImpl mailServiceImpl;

    @GetMapping("/")
    public String index() {
        return "/";
    }

    /**
     * metodo para enviar el email al profesor luego de seleccionarlo, 
     * este seria el 1er contacto
     * @param alumnoId
     * @param profesorId
     * @param subject
     * @param body
     * @return
     */
    @GetMapping("/enviarMail")
    public String enviarMail(@RequestParam(required = false) String profesorId, @RequestParam(required = false) String alumnoId, Model model) {
        System.out.println("\n\n entro a mail/enviarEmail ***************\n\n");
        
        // llenamos el email para el envio

        Alumno alumno = alumnoServiceImpl.buscarAlumno(alumnoId);
        Profesor profesor = profesorServiceImpl.buscarProfesorPorId(profesorId);
        String subject = "Contacto de SportPlanesEntrenamiento";
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
            + alumno.getAnamnesis().getOperaciones()+ " he tenido operaciones,  "
            + alumno.getAnamnesis().getObsOperaciones() + ", "
            + alumno.getAnamnesis().getEnfermedades()+ " he tenido enfermedades,  "
            + alumno.getAnamnesis().getObsEnfermedades() + ", "
            + alumno.getAnamnesis().getLimitaciones()+ " tengo limitaciones,  "
            + alumno.getAnamnesis().getObsLimitaciones() + ", "
            + alumno.getAnamnesis().getAlergias()+ " tengo alergias,  "
            + alumno.getAnamnesis().getObsAlergias() + ", "
            + alumno.getAnamnesis().getMedicamentos()+ " tomo medicamentos,  "
            + alumno.getAnamnesis().getObsMedicamentos() + ", "
            + " y mis antecedentes familiares relacionado a enfermades son "
            + alumno.getAnamnesis().getAntecedentesFamiliares().toString() + ", "              
            + " y mi email es " + alumno.getEmail();
  
        System.out.println("body : " + body);

        mailServiceImpl.enviarEmail(alumno.getEmail(), profesor.getEmail(), subject, body);
        model.addAttribute("alumno", alumno);
        return "alumno";
    }
}
