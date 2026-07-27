package com.bezkoder.spring.jpa.postgresql.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bezkoder.spring.jpa.postgresql.model.Maestro;
import com.bezkoder.spring.jpa.postgresql.service.MaestroService;

@RestController
@RequestMapping("/api/maestros")
@CrossOrigin(origins = "*")
public class MaestroController {

    @Autowired
    private MaestroService service;

    // Alta
    @PostMapping
    public ResponseEntity<Maestro> guardar(@RequestBody Maestro maestro) {

        Maestro nuevo = service.guardar(maestro);

        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }

    // Consultar todos
    @GetMapping
    public ResponseEntity<List<Maestro>> listar() {

        List<Maestro> lista = service.listar();

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    // Consultar por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id) {

        Optional<Maestro> maestro = service.buscar(id);

        if (maestro.isPresent()) {

            return new ResponseEntity<>(maestro.get(), HttpStatus.OK);

        }

        return new ResponseEntity<>("Maestro no encontrado",
                HttpStatus.NOT_FOUND);

    }

    // Modificar
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Maestro maestro) {

        Maestro actualizado = service.actualizar(id, maestro);

        if (actualizado != null) {

            return new ResponseEntity<>(actualizado, HttpStatus.OK);

        }

        return new ResponseEntity<>("Maestro no encontrado",
                HttpStatus.NOT_FOUND);

    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id) {

        if (service.eliminar(id)) {

            return new ResponseEntity<>("Registro eliminado correctamente",
                    HttpStatus.OK);

        }

        return new ResponseEntity<>("Maestro no encontrado",
                HttpStatus.NOT_FOUND);

    }

}