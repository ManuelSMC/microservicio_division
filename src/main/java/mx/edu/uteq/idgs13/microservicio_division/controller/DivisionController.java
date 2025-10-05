package mx.edu.uteq.idgs13.microservicio_division.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import mx.edu.uteq.idgs13.microservicio_division.repository.DivisionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import mx.edu.uteq.idgs13.microservicio_division.dto.DivisionDTO;
import mx.edu.uteq.idgs13.microservicio_division.entity.Division;
import mx.edu.uteq.idgs13.microservicio_division.service.DivisionService;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/divisions")
public class DivisionController {

    @Autowired
    private DivisionService service;

    // Listar todas las divisiones
    @GetMapping
    public ResponseEntity<List<DivisionDTO>> getAll() {
        List<DivisionDTO> dtos = service.findAll().stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // Buscar división por ID
    @GetMapping("/{id}")
    public ResponseEntity<DivisionDTO> getById(@PathVariable Integer id) {
        Optional<Division> division = service.findById(id);
        return division.map(d -> ResponseEntity.ok(convertToDTO(d)))
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }
  
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
    
    // Método auxiliar para convertir Entity a DTO
    private DivisionDTO convertToDTO(Division division) {
        DivisionDTO dto = new DivisionDTO();
        dto.setIdDiv(division.getIdDiv());
        dto.setNomDiv(division.getNomDiv());
        dto.setStatusDiv(division.getStatusDiv());
        
        if (division.getProgramasEducativos() != null) {
            List<DivisionDTO.ProgramaEducativoDTO> programas = division.getProgramasEducativos()
                .stream()
                .map(pe -> {
                    DivisionDTO.ProgramaEducativoDTO peDto = new DivisionDTO.ProgramaEducativoDTO();
                    peDto.setId(pe.getId());
                    peDto.setNombre(pe.getNombre());
                    return peDto;
                })
                .collect(Collectors.toList());
            dto.setProgramasEducativos(programas);
        }
        
        return dto;
    }
}
