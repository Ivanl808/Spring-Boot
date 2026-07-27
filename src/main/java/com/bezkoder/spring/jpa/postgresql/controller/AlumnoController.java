package com.bezkoder.spring.jpa.postgresql.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bezkoder.spring.jpa.postgresql.model.Alumno;
import com.bezkoder.spring.jpa.postgresql.service.AlumnoService;

@RestController
@RequestMapping("/api/alumnos")
@CrossOrigin(origins = "*")
public class AlumnoController {

    @Autowired
    private AlumnoService service;

    // Alta
    @PostMapping
    public ResponseEntity<Alumno> guardar(@RequestBody Alumno alumno) {

        Alumno nuevo = service.guardar(alumno);

        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    // Consultar todos
    @GetMapping
    public ResponseEntity<List<Alumno>> listar() {

        List<Alumno> lista = service.listar();

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // Consultar por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {

        Optional<Alumno> alumno = service.buscar(id);

        if (alumno.isPresent()) {

            return new ResponseEntity<>(alumno.get(), HttpStatus.OK);

        }

        return new ResponseEntity<>("Alumno no encontrado",
                HttpStatus.NOT_FOUND);

    }

    // Modificar
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Alumno alumno) {

        Alumno actualizado = service.actualizar(id, alumno);

        if (actualizado != null) {

            return new ResponseEntity<>(actualizado, HttpStatus.OK);

        }

        return new ResponseEntity<>("Alumno no encontrado",
                HttpStatus.NOT_FOUND);

    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        if (service.eliminar(id)) {

            return new ResponseEntity<>("Registro eliminado correctamente",
                    HttpStatus.OK);

        }

        return new ResponseEntity<>("Alumno no encontrado",
                HttpStatus.NOT_FOUND);

    }

}