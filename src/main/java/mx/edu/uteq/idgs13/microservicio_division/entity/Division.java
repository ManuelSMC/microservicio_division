package mx.edu.uteq.idgs13.microservicio_division.entity;

import jakarta.persistence.*;
import java.util.*;
import lombok.Data;

@Entity
@Table(name = "division")
@Data
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_division")
    private Integer idDivision;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "clave")
    private String clave;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "director")
    private String  director;

    /*@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "division")
    private List<ProgramaEducativo> programasEducativos;*/
}