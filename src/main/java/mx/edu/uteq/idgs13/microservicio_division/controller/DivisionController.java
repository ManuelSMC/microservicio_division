package mx.edu.uteq.idgs13.microservicio_division.controller;

import mx.edu.uteq.idgs13.microservicio_division.entity.Division;
import mx.edu.uteq.idgs13.microservicio_division.repository.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/divisiones")
@CrossOrigin(origins = "*")
public class DivisionController {
    
    @Autowired
    private DivisionRepository divisionRepository;
    
    // Actualizar/Editar una división (actualización parcial)
    @PutMapping("/{id_division}")
    public ResponseEntity<?> editar(@PathVariable Integer id_division, @RequestBody Division division) {
        try {
            Optional<Division> divisionOpt = divisionRepository.findById(id_division);
            if (divisionOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("División no encontrada con ID: " + id_division);
            }
            
            Division divisionExistente = divisionOpt.get();
            
            // Actualizar solo los campos que vienen en el request (no nulos)
            if (division.getNombre() != null) {
                divisionExistente.setNombre(division.getNombre());
            }
            
            Division divisionActualizada = divisionRepository.save(divisionExistente);
            return ResponseEntity.ok(divisionActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al actualizar la división: " + e.getMessage());
        }
    }
}

//hola, pa q se suba jaja