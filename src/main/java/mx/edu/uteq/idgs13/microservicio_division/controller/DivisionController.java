package mx.edu.uteq.idgs13.microservicio_division.controller;

import mx.edu.uteq.idgs13.microservicio_division.entity.Division;
import mx.edu.uteq.idgs13.microservicio_division.repository.DivisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/divisiones")
@CrossOrigin(origins = "*")
public class DivisionController {
    
    @Autowired
    private DivisionRepository divisionRepository;
    
    // Crear una nueva división
    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Division division) {
        try {
            Division nuevaDivision = divisionRepository.save(division);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaDivision);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al crear la división: " + e.getMessage());
        }
    }
    
    // Listar todas las divisiones
    @GetMapping
    public ResponseEntity<?> listarTodos() {
        try {
            List<Division> divisiones = divisionRepository.findAll();
            return ResponseEntity.ok(divisiones);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al listar divisiones: " + e.getMessage());
        }
    }
    
    // Obtener una división por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        try {
            Optional<Division> division = divisionRepository.findById(id);
            if (division.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("División no encontrada con ID: " + id);
            }
            return ResponseEntity.ok(division.get());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al obtener la división: " + e.getMessage());
        }
    }
    
    // Actualizar una división completa (PUT)
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Integer id, @RequestBody Division division) {
        try {
            Optional<Division> divisionExistente = divisionRepository.findById(id);
            if (divisionExistente.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("División no encontrada con ID: " + id);
            }
            
            division.setId(id);
            Division divisionActualizada = divisionRepository.save(division);
            return ResponseEntity.ok(divisionActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al actualizar la división: " + e.getMessage());
        }
    }
    
    // Actualizar parcialmente una división (PATCH)
    @PatchMapping("/{id}")
    public ResponseEntity<?> actualizarParcial(@PathVariable Integer id, @RequestBody Division divisionParcial) {
        try {
            Optional<Division> divisionOpt = divisionRepository.findById(id);
            if (divisionOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("División no encontrada con ID: " + id);
            }
            
            Division divisionExistente = divisionOpt.get();
            
            // Actualizar solo los campos que no son nulos
            if (divisionParcial.getNombre() != null) {
                divisionExistente.setNombre(divisionParcial.getNombre());
            }
            if (divisionParcial.getProgramas() != null) {
                divisionExistente.setProgramas(divisionParcial.getProgramas());
            }
            
            Division divisionActualizada = divisionRepository.save(divisionExistente);
            return ResponseEntity.ok(divisionActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al actualizar parcialmente: " + e.getMessage());
        }
    }
    
    // Eliminar una división
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            Optional<Division> division = divisionRepository.findById(id);
            if (division.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("División no encontrada con ID: " + id);
            }
            
            divisionRepository.deleteById(id);
            return ResponseEntity.ok("División eliminada exitosamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error al eliminar la división: " + e.getMessage());
        }
    }
}
