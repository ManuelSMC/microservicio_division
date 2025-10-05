package mx.edu.uteq.idgs13.microservicio_division.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.uteq.idgs13.microservicio_division.repository.DivisionRepository;
import mx.edu.uteq.idgs13.microservicio_division.repository.ProgramaEducativoRepository;
import mx.edu.uteq.idgs13.microservicio_division.entity.Division;

@Service
public class DivisionService {
    @Autowired
    private DivisionRepository divisionRepo;
    @Autowired
    private ProgramaEducativoRepository programaRepo;


    public Division crearDivision(Division division) {
        return divisionRepo.save(division);
    }

    // Habilitar división
    public Division habilitarDivision(Integer id) {
        Division division = divisionRepo.findById(id).orElseThrow(() -> new RuntimeException("División no encontrada"));
        division.setStatus(true);
        return divisionRepo.save(division);
    }

    // Deshabilitar división
    public Division deshabilitarDivision(Integer id) {
        Division division = divisionRepo.findById(id).orElseThrow(() -> new RuntimeException("División no encontrada"));
        division.setStatus(false);
        return divisionRepo.save(division);
    }

    // Borrar división
    public String deleteDivision(Integer id) {
        divisionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("División no encontrada con id: " + id));

        // Verificar si la división está siendo usada por algún programa educativo
        boolean hasProgramas = programaRepo.existsById(id);

        if (hasProgramas) {
            return "La división está siendo usada y no puede ser eliminada.";
        } else {
            // Si no está en uso, eliminar la división
            divisionRepo.deleteById(id);
            return "División eliminada exitosamente.";
        }
    }
}