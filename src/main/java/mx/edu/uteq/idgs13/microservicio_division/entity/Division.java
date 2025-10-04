package mx.edu.uteq.idgs13.microservicio_division.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import jakarta.persistence.*;
import java.util.*;

@Entity
@Data
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProgramaEducativo> programas;
    
    
    private Boolean habilitado = true;  
}