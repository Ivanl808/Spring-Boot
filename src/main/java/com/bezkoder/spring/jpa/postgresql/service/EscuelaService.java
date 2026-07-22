package com.bezkoder.spring.jpa.postgresql.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.jpa.postgresql.model.Escuela;
import com.bezkoder.spring.jpa.postgresql.repository.EscuelaRepository;

@Service
public class EscuelaService {

    @Autowired
    private EscuelaRepository repository;

    // Guardar
    public Escuela guardar(Escuela escuela) {
        return repository.save(escuela);
    }

    // Listar
    public List<Escuela> listar() {
        return repository.findAll();
    }

    // Buscar por ID
    public Optional<Escuela> buscar(Long id) {
        return repository.findById(id);
    }

    // Actualizar
    public Escuela actualizar(Long id, Escuela escuela) {

        Optional<Escuela> datos = repository.findById(id);

        if (datos.isPresent()) {

            Escuela existente = datos.get();

            existente.setNombre(escuela.getNombre());
            existente.setDireccion(escuela.getDireccion());
            existente.setTelefono(escuela.getTelefono());

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