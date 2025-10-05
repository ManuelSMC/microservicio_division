package mx.edu.uteq.idgs13.microservicio_division.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.uteq.idgs13.microservicio_division.entity.Division;
import mx.edu.uteq.idgs13.microservicio_division.repository.DivisionRepository;

@Service
public class DivisionServiceImpl implements DivisionService {

    @Autowired
    private DivisionRepository repository;

    @Override
    public List<Division> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Division> findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Division> findByNombre(String nombre) {
        return repository.findByNomDivContainingIgnoreCase(nombre);
    }

    @Override
    public List<Division> findByStatus(Boolean status) {
        return repository.findByStatusDiv(status);
    }

    @Override
    public List<Division> findByNombreAndStatus(String nombre, Boolean status) {
        return repository.findByNomDivContainingIgnoreCaseAndStatusDiv(nombre, status);
    }
}