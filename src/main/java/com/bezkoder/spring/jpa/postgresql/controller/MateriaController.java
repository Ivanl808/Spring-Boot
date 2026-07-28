package com.bezkoder.spring.jpa.postgresql.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.bezkoder.spring.jpa.postgresql.model.Materia;
import com.bezkoder.spring.jpa.postgresql.service.MateriaService;

@RestController
@RequestMapping("/api/materias")
@CrossOrigin(origins = "*")
public class MateriaController {

    @Autowired
    private MateriaService service;

    @PostMapping
    public ResponseEntity<Materia> guardar(@RequestBody Materia materia) {

        return new ResponseEntity<>(service.guardar(materia), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Materia>> listar() {

        return new ResponseEntity<>(service.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {

        Optional<Materia> materia = service.buscar(id);

        if (materia.isPresent()) {
            return new ResponseEntity<>(materia.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>("Materia no encontrada", HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Materia materia) {

        Materia actualizada = service.actualizar(id, materia);

        if (actualizada != null) {
            return new ResponseEntity<>(actualizada, HttpStatus.OK);
        }

        return new ResponseEntity<>("Materia no encontrada", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        if (service.eliminar(id)) {
            return new ResponseEntity<>("Materia eliminada", HttpStatus.OK);
        }

        return new ResponseEntity<>("Materia no encontrada", HttpStatus.NOT_FOUND);
    }

}