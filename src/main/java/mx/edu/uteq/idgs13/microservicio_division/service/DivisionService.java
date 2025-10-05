package mx.edu.uteq.idgs13.microservicio_division.service;

import java.util.List;
import java.util.Optional;

import mx.edu.uteq.idgs13.microservicio_division.entity.Division;
import mx.edu.uteq.idgs13.microservicio_division.repository.DivisionRepository;

@Service
public class DivisionService {

    @Autowired
    private DivisionRepository divisionRepo;

    public Division crearDivision(Division division) {
        return divisionRepo.save(division);
    }
}
