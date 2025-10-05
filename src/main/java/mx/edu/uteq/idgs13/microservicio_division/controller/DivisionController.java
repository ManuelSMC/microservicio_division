package mx.edu.uteq.idgs13.microservicio_division.controller;

import java.util.List;
import java.util.Optional;
import mx.edu.uteq.idgs13.microservicio_division.repository.DivisionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import mx.edu.uteq.idgs13.microservicio_division.entity.Division;
import mx.edu.uteq.idgs13.microservicio_division.service.DivisionService;

@RestController
@RequestMapping("/api/divisiones")
public class DivisionController {

    @Autowired
    private DivisionRepository divisionRepository;

    // Listar todas las divisiones
    @GetMapping
    public ResponseEntity<List<Division>> getAll() {
        List<Division> divisiones = divisionRepository.findAll();
        return ResponseEntity.ok(divisiones);
    }

    // Buscar división por ID
    @GetMapping("/{id}")
    public ResponseEntity<Division> getById(@PathVariable Integer id) {
        Optional<Division> division = divisionRepository.findById(id);
        if (division.isPresent()) {
            return ResponseEntity.ok(division.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
  
    // Editar una división (actualización parcial)
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
  
    @PutMapping("/{id}/habilitar")
    public ResponseEntity<Division> habilitarDivision(@PathVariable Integer id) {
        try {
            Division updated = divisionService.habilitarDivision(id);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT deshabilitar
    @PutMapping("/{id}/deshabilitar")
    public ResponseEntity<Division> deshabilitarDivision(@PathVariable Integer id) {
        try {
            Division updated = divisionService.deshabilitarDivision(id);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDivision(@PathVariable Integer id) {
        try {
            divisionService.deleteDivision(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
