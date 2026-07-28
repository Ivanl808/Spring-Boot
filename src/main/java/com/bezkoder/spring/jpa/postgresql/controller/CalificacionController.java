package com.bezkoder.spring.jpa.postgresql.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bezkoder.spring.jpa.postgresql.model.Calificacion;
import com.bezkoder.spring.jpa.postgresql.service.CalificacionService;

@RestController
@RequestMapping("/api/calificaciones")
@CrossOrigin(origins = "*")
public class CalificacionController {

    @Autowired
    private CalificacionService service;

    // Alta
    @PostMapping
    public ResponseEntity<Calificacion> guardar(@RequestBody Calificacion calificacion) {

        Calificacion nueva = service.guardar(calificacion);

        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }

    // Consultar todas
    @GetMapping
    public ResponseEntity<List<Calificacion>> listar() {

        List<Calificacion> lista = service.listar();

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // Consultar por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {

        Optional<Calificacion> calificacion = service.buscar(id);

        if (calificacion.isPresent()) {

            return new ResponseEntity<>(calificacion.get(), HttpStatus.OK);

        }

        return new ResponseEntity<>("Calificación no encontrada", HttpStatus.NOT_FOUND);
    }

    // Modificar
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Calificacion calificacion) {

        Calificacion actualizada = service.actualizar(id, calificacion);

        if (actualizada != null) {

            return new ResponseEntity<>(actualizada, HttpStatus.OK);

        }

        return new ResponseEntity<>("Calificación no encontrada", HttpStatus.NOT_FOUND);
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        if (service.eliminar(id)) {

            return new ResponseEntity<>("Calificación eliminada correctamente",
                    HttpStatus.OK);

        }

        return new ResponseEntity<>("Calificación no encontrada",
                HttpStatus.NOT_FOUND);
    }

}