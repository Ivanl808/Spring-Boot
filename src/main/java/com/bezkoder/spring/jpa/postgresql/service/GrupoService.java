package com.bezkoder.spring.jpa.postgresql.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.jpa.postgresql.model.Grupo;
import com.bezkoder.spring.jpa.postgresql.repository.GrupoRepository;


@Service
public class GrupoService {


    @Autowired
    private GrupoRepository repository;


    public Grupo guardar(Grupo grupo){
        return repository.save(grupo);
    }


    public List<Grupo> listar(){
        return repository.findAll();
    }


    public Optional<Grupo> buscar(Long id){
        return repository.findById(id);
    }


    public Grupo actualizar(Long id, Grupo grupo){

        if(repository.existsById(id)){

            grupo.setIdGrupo(id);

            return repository.save(grupo);
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