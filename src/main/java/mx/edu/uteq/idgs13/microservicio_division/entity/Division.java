package mx.edu.uteq.idgs13.microservicio_division.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "division")
@Data
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_div")
    private Integer idDiv;

    @Column(name = "nom_div", nullable = false)
    private String nomDiv;

    @Column(name = "status_div", nullable = false)
    private Boolean statusDiv;

    @OneToMany(mappedBy = "division", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgramaEducativo> programasEducativos;
}