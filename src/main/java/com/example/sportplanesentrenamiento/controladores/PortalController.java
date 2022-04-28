package com.example.sportplanesentrenamiento.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class PortalController {
    
    @GetMapping({""})
    public String index(){
        return "index";
    }   
    
//    //    @PreAuthorize("hasAnyRole('ROLE_PROFESOR_REGISTRADO')")
//    @GetMapping({"/index", "/home", "/"})
//    public String index(String cosa, Model modelo) {
//        cosa = "Hola Spring lcdtm";
//        modelo.addAttribute("texto", cosa);
//
//        return "index";
//    }
    
    @GetMapping("/bienvenido")
    public String bienvenido(){
        return "BienvenidoTemplate";
    }
    
    /**
     * metodo que selecciona la registracion de un alumno o un profesor
     * @return 
     */
    @GetMapping("/nuevoRegistro")
    public String nuevoRegistro(){
        return "registracion";            
    }
    

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, @RequestParam(required = false) String logout , Model model){
        System.out.println("\n entro a /login -----------------\n");
        
        if (error != null){
            model.addAttribute("error","Nombre de Usuario o Clave incorrectos");
        }
        if (logout != null){
            model.addAttribute("logout","Ha salido correctamente de la plataforma");
        }
        return "login";
    }
    
    /**
     * metodo que dispara a ingresar un alumno o un profesor
     * @param valor
     * @return 
     */
    @GetMapping("/registracion")
    public String registracion(String valor){
        System.out.println("\n entro a registracion ********** \n\n");
        System.out.println("valor : " + valor);
        
        if(valor.equals("1")){
            return "redirect:/alumno/crear";
        }
        if(valor.equals("2")){
            return "redirect:/profesor/ingresar-profesor";
        }
        return null;
        
    }
    
    
    
    
//    @PostMapping("/registro-usuario")
//    public String redireccionDeRegistro(@RequestParam(name = "act", required = false) String act, Model Model) {
//        System.out.println("profesor lo que ingreso fue: " + act);
//
//        if (act.equalsIgnoreCase("profesor")){
//            return "redirect:/profesor/ingresar-profesor";
//        }else if (act.equalsIgnoreCase("alumno")){
//            return "redirect:/alumno/ingresar-alumno";
//        }
//
//        return null;
//    }
    
//    
//    @GetMapping("/usuarioRegistrado")
//    public String usuarioRegistrado(){
//        return "login";            
//    }
    
    /*
    --------------------------------------------------------------
    */
    
       
}
