package com.bezkoder.spring.jpa.postgresql.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "alumno")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_alumno")
    private Long idAlumno;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private Integer edad;

    @Column(nullable = false)
    private Integer semestre;


    // Relación con Grupo
    // Muchos alumnos pertenecen a un grupo
    @ManyToOne
    @JoinColumn(name = "id_grupo")
    private Grupo grupo;
    
    @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Calificacion> calificaciones;

    public Alumno() {
    }


    public Alumno(Long idAlumno, String nombre, String apellido,
                  Integer edad, Integer semestre,
                  Grupo grupo) {

        this.idAlumno = idAlumno;
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.semestre = semestre;
        this.grupo = grupo;
    }


    public Long getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Long idAlumno) {
        this.idAlumno = idAlumno;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }


    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }


    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

}