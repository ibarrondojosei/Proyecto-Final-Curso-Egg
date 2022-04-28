/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.sportplanesentrenamiento.servicios;

import com.example.sportplanesentrenamiento.entidades.Provincia;
import com.example.sportplanesentrenamiento.repository.ProvinciaDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinciaServiceImpl implements ProvinciaServiceI{
    
    @Autowired
    private ProvinciaDao provinciaDao;
    
    @Override
    public List<Provincia> listarProvincias() {
        return provinciaDao.findAll();
    }

    @Override
    public List<Provincia> listaOrdenadaPorNombre() {
        return provinciaDao.listaOrdenadaPorNombre();
    }
    
}
