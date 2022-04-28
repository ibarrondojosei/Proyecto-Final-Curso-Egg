
package com.example.sportplanesentrenamiento.servicios;

import com.example.sportplanesentrenamiento.entidades.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl {
    
    @Autowired
    private JavaMailSender javaMailSender;  
    
    @Autowired
    private ProfesorServiceImpl profesorServiceImpl;
    
    /**
     * aqui recibimos el alumno(from-remitente)y del profesor(to-destinatario)
     * el asunto(subject) y el mensaje para el primer contacto(Body)
     * @param from
     * @param to
     * @param subject
     * @param body 
     */
    public void enviarEmail(String alumnoId, String profesorId, String subject, String body){
        System.out.println("\n entro a sendMail ************** \n");
        
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(alumnoId);
        message.setTo(profesorId);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
        
        System.out.println("\n envio teoricamente el mail \n");
    }  
    
}
