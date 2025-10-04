package mx.edu.uteq.idgs13.microservicio_division.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DivisionDTO {
    private Integer idDiv;
    private String nomDiv;
    private Boolean statusDiv;
    private List<ProgramaEducativoDTO> programasEducativos;
    
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ProgramaEducativoDTO {
        private Integer id;
        private String nombre;
    }
}