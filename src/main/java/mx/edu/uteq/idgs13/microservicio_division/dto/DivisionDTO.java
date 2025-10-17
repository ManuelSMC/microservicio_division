package mx.edu.uteq.idgs13.microservicio_division.dto;

import mx.edu.uteq.idgs13.microservicio_division.entity.Division;

public class DivisionDTO {
    private Integer idDivision;
    private String nombre;
    private Boolean status;
    private String clave;
    private String descripcion;
    private String director;

    public DivisionDTO() {}

    public DivisionDTO(Integer idDivision, String nombre, Boolean status, String clave, String descripcion, String director) {
        this.idDivision = idDivision;
        this.nombre = nombre;
        this.status = status;
        this.clave = clave;
        this.descripcion = descripcion;
        this.director = director;
    }

    public static DivisionDTO fromEntity(Division d) {
        if (d == null) return null;
        return new DivisionDTO(
            d.getIdDivision(),
            d.getNombre(),
            d.getStatus(),
            d.getClave(),
            d.getDescripcion(),
            d.getDirector()
        );
    }

    // getters y setters
    public Integer getIdDivision() { return idDivision; }
    public void setIdDivision(Integer idDivision) { this.idDivision = idDivision; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public Boolean getStatus() { return status; }
    public void setStatus(Boolean status) { this.status = status; }
    public String getClave() { return clave; }
    public void setClave(String clave) { this.clave = clave; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getDirector() { return director; }
    public void setDirector(String director) { this.director = director; }
}