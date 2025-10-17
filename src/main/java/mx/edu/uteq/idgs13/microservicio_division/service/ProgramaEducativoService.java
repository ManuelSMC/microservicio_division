package mx.edu.uteq.idgs13.microservicio_division.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.uteq.idgs13.microservicio_division.dto.ProgramaEducativoDTO;
import mx.edu.uteq.idgs13.microservicio_division.entity.ProgramaEducativo;
import mx.edu.uteq.idgs13.microservicio_division.repository.ProgramaEducativoRepository;

@Service
public class ProgramaEducativoService {
    @Autowired
    private ProgramaEducativoRepository programaRepo;

    
    public ProgramaEducativo crearPrograma(ProgramaEducativoDTO dto) {
        ProgramaEducativo programa = new ProgramaEducativo();
        mapDtoToEntity(dto, programa);
        return programaRepo.save(programa);
    }

    public ProgramaEducativo editarPrograma(Integer id, ProgramaEducativoDTO dto) {
        ProgramaEducativo programa = programaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Programa Educativo no encontrado"));
        mapDtoToEntity(dto, programa);
        return programaRepo.save(programa);
    }

    private void mapDtoToEntity(ProgramaEducativoDTO dto, ProgramaEducativo programa) {
        programa.setNombre(dto.getNombre());
        programa.setStatus(dto.getStatus());
        programa.setClave(dto.getClave());
        programa.setDescripcion(dto.getDescripcion());
    }

    // Habilitar programa educativo
    public ProgramaEducativo habilitarPrograma(Integer id) {
        ProgramaEducativo programa = programaRepo.findById(id).orElseThrow(() -> new RuntimeException("Programa educativo no encontrado"));
        programa.setStatus(true);
        return programaRepo.save(programa);
    }

    // Deshabilitar programa educativo
    public ProgramaEducativo deshabilitarPrograma(Integer id) {
        ProgramaEducativo programa = programaRepo.findById(id).orElseThrow(() -> new RuntimeException("Programa educativo no encontrado"));
        programa.setStatus(false);
        return programaRepo.save(programa);
    }

    // Borrar programa educativo
    public String deletePrograma(Integer id) {
        programaRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Programa educativo no encontrado"));

        // Verificar si el programa educativo está siendo usado
        boolean usado = programaRepo.existsById(id);

        if (usado) {
            return "El programa educativo está siendo usado y no puede ser eliminado.";
        } else {
            // Si no está en uso, eliminar el programa educativo
            programaRepo.deleteById(id);
            return "Programa educativo eliminado exitosamente.";
        }
    }
}
