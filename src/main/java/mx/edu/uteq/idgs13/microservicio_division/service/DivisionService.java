package mx.edu.uteq.idgs13.microservicio_division.service;

import java.util.List;
import java.util.Optional;

import mx.edu.uteq.idgs13.microservicio_division.entity.Division;

public interface DivisionService {
    
    
    List<Division> findAll();
    Optional<Division> findById(Integer id);
    
  
    List<Division> findByNombre(String nombre);
    List<Division> findByStatus(Boolean status);
    List<Division> findByNombreAndStatus(String nombre, Boolean status);
}