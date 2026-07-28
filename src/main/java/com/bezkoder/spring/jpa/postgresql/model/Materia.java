package com.bezkoder.spring.jpa.postgresql.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "materia")
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materia")
    private Long idMateria;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer creditos;

    @Column(nullable = false)
    private Integer horasSemanales;

    // Muchas materias pertenecen a un maestro
    @ManyToOne
    @JoinColumn(name = "id_maestro")
    private Maestro maestro;
    
    @OneToMany(mappedBy = "materia", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Calificacion> calificaciones;

    public Materia() {
    }

    public Materia(Long idMateria, String nombre,
                   Integer creditos, Integer horasSemanales,
                   Maestro maestro) {
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.creditos = creditos;
        this.horasSemanales = horasSemanales;
        this.maestro = maestro;
    }

    public Long getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Long idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

    public Integer getHorasSemanales() {
        return horasSemanales;
    }

    public void setHorasSemanales(Integer horasSemanales) {
        this.horasSemanales = horasSemanales;
    }

    public Maestro getMaestro() {
        return maestro;
    }

    public void setMaestro(Maestro maestro) {
        this.maestro = maestro;
    }

}