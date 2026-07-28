package com.bezkoder.spring.jpa.postgresql.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.jpa.postgresql.model.Calificacion;
import com.bezkoder.spring.jpa.postgresql.repository.CalificacionRepository;

@Service
public class CalificacionService {

    @Autowired
    private CalificacionRepository repository;

    public Calificacion guardar(Calificacion calificacion){
        return repository.save(calificacion);
    }

    public List<Calificacion> listar(){
        return repository.findAll();
    }

    public Optional<Calificacion> buscar(Long id){
        return repository.findById(id);
    }

    public Calificacion actualizar(Long id, Calificacion calificacion){

        if(repository.existsById(id)){
            calificacion.setIdCalificacion(id);
            return repository.save(calificacion);
        }

        return null;
    }

    public boolean eliminar(Long id){

        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }

        return false;
    }

}