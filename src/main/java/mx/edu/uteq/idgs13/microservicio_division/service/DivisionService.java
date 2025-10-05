package mx.edu.uteq.idgs13.microservicio_division.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.uteq.idgs13.microservicio_division.repository.DivisionRepository;
import mx.edu.uteq.idgs13.microservicio_division.repository.ProgramaEducativoRepository;
import mx.edu.uteq.idgs13.microservicio_division.entity.Division;
import java.util.List;
import java.util.Optional;

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
        division.setHabilitado(true);
        return divisionRepo.save(division);
    }

    // Deshabilitar división
    public Division deshabilitarDivision(Integer id) {
        Division division = divisionRepo.findById(id).orElseThrow(() -> new RuntimeException("División no encontrada"));
        division.setHabilitado(false);
        return divisionRepo.save(division);
    }

    // Borrar división (soft delete? No, hard delete como pediste)
    public void deleteDivision(Integer id) {
        if (divisionRepo.existsById(id)) {
            divisionRepo.deleteById(id);
        } else {
            throw new RuntimeException("División no encontrada");
        }
    }
}