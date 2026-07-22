package com.bezkoder.spring.jpa.postgresql.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bezkoder.spring.jpa.postgresql.model.Escuela;
import com.bezkoder.spring.jpa.postgresql.service.EscuelaService;

@RestController
@RequestMapping("/api/escuelas")
@CrossOrigin(origins = "*")
public class EscuelaController {

    @Autowired
    private EscuelaService service;

    // Alta
    @PostMapping
    public ResponseEntity<Escuela> guardar(@RequestBody Escuela escuela) {

        Escuela nueva = service.guardar(escuela);

        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    // Consultar todos
    @GetMapping
    public ResponseEntity<List<Escuela>> listar() {

        List<Escuela> lista = service.listar();

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // Consultar por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {

        Optional<Escuela> escuela = service.buscar(id);

        if (escuela.isPresent()) {

            return new ResponseEntity<>(escuela.get(), HttpStatus.OK);

        }

        return new ResponseEntity<>("Escuela no encontrada", HttpStatus.NOT_FOUND);

    }

    // Modificar
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Escuela escuela) {

        Escuela actualizada = service.actualizar(id, escuela);

        if (actualizada != null) {

            return new ResponseEntity<>(actualizada, HttpStatus.OK);

        }

        return new ResponseEntity<>("Escuela no encontrada", HttpStatus.NOT_FOUND);

    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        if (service.eliminar(id)) {

            return new ResponseEntity<>("Registro eliminado correctamente",
                    HttpStatus.OK);

        }

        return new ResponseEntity<>("Escuela no encontrada",
                HttpStatus.NOT_FOUND);

    }

}