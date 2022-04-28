/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sportplanesentrenamiento.servicios;

import com.example.sportplanesentrenamiento.entidades.Provincia;
import java.util.List;

public interface ProvinciaServiceI {
    
    public List<Provincia> listarProvincias();
        
    public List<Provincia> listaOrdenadaPorNombre(); 
   
    
}
