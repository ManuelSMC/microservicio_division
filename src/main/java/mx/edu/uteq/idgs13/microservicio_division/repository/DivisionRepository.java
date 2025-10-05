package mx.edu.uteq.idgs13.microservicio_division.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.edu.uteq.idgs13.microservicio_division.entity.Division;

@Repository
public interface DivisionRepository extends JpaRepository<Division, Integer>{
    
}