package com.bezkoder.spring.jpa.postgresql.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bezkoder.spring.jpa.postgresql.model.Maestro;
import com.bezkoder.spring.jpa.postgresql.repository.MaestroRepository;

@Service
public class MaestroService {

    @Autowired
    private MaestroRepository repository;

    // Guardar
    public Maestro guardar(Maestro maestro) {
        return repository.save(maestro);
    }

    // Listar
    public List<Maestro> listar() {
        return repository.findAll();
    }

    // Buscar por ID
    public Optional<Maestro> buscar(Long id) {
        return repository.findById(id);
    }

    // Actualizar
    public Maestro actualizar(Long id, Maestro maestro) {

        Optional<Maestro> datos = repository.findById(id);

        if (datos.isPresent()) {

            Maestro existente = datos.get();

            existente.setNombre(maestro.getNombre());
            existente.setApellido(maestro.getApellido());
            existente.setEspecialidad(maestro.getEspecialidad());
            existente.setTelefono(maestro.getTelefono());
            existente.setEscuela(maestro.getEscuela());

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