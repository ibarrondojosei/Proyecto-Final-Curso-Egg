package com.example.sportplanesentrenamiento;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registro) {
        
       WebMvcConfigurer.super.addResourceHandlers(registro);
       
//       registro.addResourceHandler("/uploads/**").addResourceLocations("file:/C:/EGG/ProyectoFinalEgg/uploads/");
       
       registro.addResourceHandler("/uploads/**").addResourceLocations("file:/C:/Temp/uploads/");

    }

}
