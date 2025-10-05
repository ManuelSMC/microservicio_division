package mx.edu.uteq.idgs13.microservicio_division.entity;

import jakarta.persistence.*;
import java.util.*;
import lombok.Data;

import java.util.List;


@Entity
@Table(name = "division")
@Data
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_division;
    private String nombre;
    private Boolean status;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgramaEducativo> programasEducativos;
}