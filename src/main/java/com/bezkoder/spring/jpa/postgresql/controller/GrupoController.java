package com.bezkoder.spring.jpa.postgresql.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bezkoder.spring.jpa.postgresql.model.Grupo;
import com.bezkoder.spring.jpa.postgresql.service.GrupoService;


@RestController
@RequestMapping("/api/grupos")
@CrossOrigin(origins = "*")
public class GrupoController {


    @Autowired
    private GrupoService service;


    // Alta
    @PostMapping
    public ResponseEntity<Grupo> guardar(@RequestBody Grupo grupo){

        Grupo nuevo = service.guardar(grupo);

        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }


    // Consultar todos
    @GetMapping
    public ResponseEntity<List<Grupo>> listar(){

        List<Grupo> lista = service.listar();

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }


    // Consultar por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable Long id){

        Optional<Grupo> grupo = service.buscar(id);

        if(grupo.isPresent()){

            return new ResponseEntity<>(grupo.get(), HttpStatus.OK);

        }

        return new ResponseEntity<>("Grupo no encontrado",
                HttpStatus.NOT_FOUND);
    }


    // Modificar
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(
            @PathVariable Long id,
            @RequestBody Grupo grupo){

        Grupo actualizado = service.actualizar(id, grupo);

        if(actualizado != null){

            return new ResponseEntity<>(actualizado,
                    HttpStatus.OK);
        }

        return new ResponseEntity<>("Grupo no encontrado",
                HttpStatus.NOT_FOUND);
    }


    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Long id){

        if(service.eliminar(id)){

            return new ResponseEntity<>(
                    "Grupo eliminado correctamente",
                    HttpStatus.OK);
        }

        return new ResponseEntity<>(
                "Grupo no encontrado",
                HttpStatus.NOT_FOUND);
    }
}