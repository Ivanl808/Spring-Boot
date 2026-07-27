package com.bezkoder.spring.jpa.postgresql.model;

import java.util.List;

import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "escuela")
public class Escuela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_escuela")
    private Long idEscuela;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String telefono;

    // Relación con Maestro (Una escuela tiene muchos maestros)
    @OneToMany(mappedBy = "escuela", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Maestro> maestros;

    // Relación con Alumno (Una escuela tiene muchos alumnos)
    @OneToMany(mappedBy = "escuela", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Alumno> alumnos;

    public Escuela() {
    }

    public Escuela(Long idEscuela, String nombre, String direccion, String telefono) {
        this.idEscuela = idEscuela;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    public Long getIdEscuela() {
        return idEscuela;
    }

    public void setIdEscuela(Long idEscuela) {
        this.idEscuela = idEscuela;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Maestro> getMaestros() {
        return maestros;
    }

    public void setMaestros(List<Maestro> maestros) {
        this.maestros = maestros;
    }

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }

}