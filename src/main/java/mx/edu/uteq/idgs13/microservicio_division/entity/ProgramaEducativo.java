package mx.edu.uteq.idgs13.microservicio_division.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "programa_educativo")
@Data
public class ProgramaEducativo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_programa_educativo")
    private Integer idProgramaEducativo;

    @Column(name = "nombre")
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "division_id")
    private Division division;
}