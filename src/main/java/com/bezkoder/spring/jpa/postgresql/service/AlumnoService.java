package com.bezkoder.spring.jpa.postgresql.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.jpa.postgresql.model.Alumno;
import com.bezkoder.spring.jpa.postgresql.repository.AlumnoRepository;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository repository;

    // Guardar
    public Alumno guardar(Alumno alumno) {
        return repository.save(alumno);
    }

    // Listar
    public List<Alumno> listar() {
        return repository.findAll();
    }

    // Buscar por ID
    public Optional<Alumno> buscar(Long id) {
        return repository.findById(id);
    }

    // Actualizar
    public Alumno actualizar(Long id, Alumno alumno) {

        Optional<Alumno> datos = repository.findById(id);

        if (datos.isPresent()) {

            Alumno existente = datos.get();

            existente.setNombre(alumno.getNombre());
            existente.setApellido(alumno.getApellido());
            existente.setEdad(alumno.getEdad());
            existente.setSemestre(alumno.getSemestre());
            existente.setEscuela(alumno.getEscuela());

            return repository.save(existente);
        }

        return null;
    }

    // Eliminar
    public boolean eliminar(Long id) {

        if (repository.existsById(id)) {

            repository.deleteById(id);
            return true;

        }

        return false;
    }

}