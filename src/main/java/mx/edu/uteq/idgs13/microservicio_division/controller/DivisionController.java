package mx.edu.uteq.idgs13.microservicio_division.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.uteq.idgs13.microservicio_division.dto.DivisionDTO;
import mx.edu.uteq.idgs13.microservicio_division.entity.Division;
import mx.edu.uteq.idgs13.microservicio_division.service.DivisionService;

@RestController
@RequestMapping("/api/divisions")
public class DivisionController {

    @Autowired
    private DivisionService service;

    // Búsqueda general con filtros opcionales
    @GetMapping("/search")
    public ResponseEntity<List<DivisionDTO>> search(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) Boolean status) {
        
        List<Division> divisiones;
        
        if (nombre != null && status != null) {
            divisiones = service.findByNombreAndStatus(nombre, status);
        } else if (nombre != null) {
            divisiones = service.findByNombre(nombre);
        } else if (status != null) {
            divisiones = service.findByStatus(status);
        } else {
            divisiones = service.findAll();
        }
        
        List<DivisionDTO> dtos = divisiones.stream()
            .map(this::convertToDTO)
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(dtos);
    }

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