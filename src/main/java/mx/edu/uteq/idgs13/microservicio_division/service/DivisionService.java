package mx.edu.uteq.idgs13.microservicio_division.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.uteq.idgs13.microservicio_division.repository.DivisionRepository;
import mx.edu.uteq.idgs13.microservicio_division.repository.ProgramaEducativoRepository;

@Service
public class DivisionService {
    @Autowired
    private DivisionRepository divisionRepo;
    @Autowired
    private ProgramaEducativoRepository programaRepo;
}
// asd