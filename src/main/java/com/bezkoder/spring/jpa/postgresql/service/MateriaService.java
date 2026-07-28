package com.bezkoder.spring.jpa.postgresql.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.jpa.postgresql.model.Materia;
import com.bezkoder.spring.jpa.postgresql.repository.MateriaRepository;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository repository;

    public Materia guardar(Materia materia) {
        return repository.save(materia);
    }

    public List<Materia> listar() {
        return repository.findAll();
    }

    public Optional<Materia> buscar(Long id) {
        return repository.findById(id);
    }

    public Materia actualizar(Long id, Materia materia) {

        if (repository.existsById(id)) {

            materia.setIdMateria(id);
            return repository.save(materia);
        }

        return null;
    }

    public boolean eliminar(Long id) {

        if (repository.existsById(id)) {

            repository.deleteById(id);
            return true;
        }

        return false;
    }

}