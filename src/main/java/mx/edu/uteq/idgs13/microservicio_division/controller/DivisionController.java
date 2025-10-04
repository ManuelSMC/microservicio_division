package mx.edu.uteq.idgs13.microservicio_division.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mx.edu.uteq.idgs13.microservicio_division.service.DivisionService;
import mx.edu.uteq.idgs13.microservicio_division.entity.Division;
import java.util.List;
import java.util.Optional;

@RestController  
@RequestMapping("/division") 
public class DivisionController {

    @Autowired
    private DivisionService divisionService;

    // GET todas las divisiones
    @GetMapping
    public List<Division> getAllDivisiones() {
        return divisionService.getAllDivisiones();
    }

    // GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<Division> getDivisionById(@PathVariable Integer id) {
        Optional<Division> division = divisionService.getDivisionById(id);
        return division.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    // PUT habilitar
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

    // DELETE borrar
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