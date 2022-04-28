package com.example.sportplanesentrenamiento.servicios;

import com.example.sportplanesentrenamiento.entidades.Profesor;
import com.example.sportplanesentrenamiento.entidades.Valoracion;
import com.example.sportplanesentrenamiento.repository.ValoracionDao;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValoracionServiceImpl implements ValoracionServiceI {

    @Autowired
    private ValoracionDao valoracionDao;

    @Autowired
    private ProfesorServiceImpl profesorServiceImpl;

    @Override
    public List<Integer> listarValoraciones(String profesorId) {
        return valoracionDao.listaValoracion(profesorId);
    }

    @Override
    public Valoracion buscarValoracionPorId(String id) {
        return valoracionDao.findById(id).orElse(null);
    }

    @Override
    public void guardarValoracion(Valoracion valoracion) {
        valoracionDao.save(valoracion);
    }

    @Override
    public void borrarValoracionPorId(String id) {
        valoracionDao.deleteById(id);
    }

    public void promedioValoracion(String profesorId) throws ParseException {

        System.out.println("valor : " + listarValoraciones(profesorId));

        Profesor profesor = new Profesor();
        profesor = profesorServiceImpl.buscarProfesorPorId(profesorId);

        double suma = 0;
        double tamanio = listarValoraciones(profesorId).size();
        for (Integer valor : listarValoraciones(profesorId)) {
            suma = suma + valor;
            System.out.println("valor : " + valor);
            System.out.println("suma : " + suma);
        }

        double promedio1 = suma / tamanio;
        
        DecimalFormat formateador = new DecimalFormat("0.#");
        double promedio = promedio1;
        promedio = formateador.parse(formateador.format(promedio)).doubleValue();

        /*
            aqui seteamos setPromedio y luego actualizamos/guardar el profesor
         */
        profesor.setPromedio(promedio);

        profesorServiceImpl.guardarProfesorSinImagen(profesor);

        System.out.println("tamanio : " + tamanio);
        System.out.println("Promedio : " + promedio);

    }

    @Override
    public List<Valoracion> listarValoraciones_1() {
       return valoracionDao.findAll();
    }
}
