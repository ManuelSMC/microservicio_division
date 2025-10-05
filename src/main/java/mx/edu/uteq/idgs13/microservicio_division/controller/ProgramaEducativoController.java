package mx.edu.uteq.idgs13.microservicio_division.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.uteq.idgs13.microservicio_division.dto.ProgramaEducativoDTO;
import mx.edu.uteq.idgs13.microservicio_division.entity.ProgramaEducativo;
import mx.edu.uteq.idgs13.microservicio_division.repository.ProgramaEducativoRepository;
import mx.edu.uteq.idgs13.microservicio_division.service.ProgramaEducativoService;

@RestController
@RequestMapping("/api/programas")
public class ProgramaEducativoController {
    @Autowired
    private ProgramaEducativoRepository programaRepository;

    @Autowired
    private ProgramaEducativoService programaService;

    // Crear una nuevo programa educativo
    @PostMapping
    public ResponseEntity<ProgramaEducativo> crearPrograma(@RequestBody ProgramaEducativoDTO dto) {
        System.out.println(dto.getDivisionId());
        ProgramaEducativo programa = programaService.crearPrograma(dto);
        return ResponseEntity.ok(programa);
    }

    // Listar todas los programas educativos
    @GetMapping
    public ResponseEntity<List<ProgramaEducativo>> getAll() {
        List<ProgramaEducativo> programas = programaRepository.findAll();
        return ResponseEntity.ok(programas);
    }

    // Buscar programa educativo por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProgramaEducativo> getById(@PathVariable Integer id) {
        Optional<ProgramaEducativo> programa = programaRepository.findById(id);
        if (programa.isPresent()) {
            return ResponseEntity.ok(programa.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
  
    @PutMapping("/{id}")
    public ResponseEntity<ProgramaEducativo> editarPrograma(@PathVariable Integer id, @RequestBody ProgramaEducativoDTO dto) {
        ProgramaEducativo programa = programaService.editarPrograma(id, dto);
        return ResponseEntity.ok(programa);
    }

    // PUT habilitar
    @PutMapping("/{id}/habilitar")
    public ResponseEntity<ProgramaEducativo> habilitarPrograma(@PathVariable Integer id) {
        try {
            ProgramaEducativo updated = programaService.habilitarPrograma(id);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // PUT deshabilitar
    @PutMapping("/{id}/deshabilitar")
    public ResponseEntity<ProgramaEducativo> deshabilitarPrograma(@PathVariable Integer id) {
        try {
            ProgramaEducativo updated = programaService.deshabilitarPrograma(id);
            return ResponseEntity.ok(updated);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE 
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deletePrograma(@PathVariable Integer id) {
        try {
            String message = programaService.deletePrograma(id);
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", message);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }
}
