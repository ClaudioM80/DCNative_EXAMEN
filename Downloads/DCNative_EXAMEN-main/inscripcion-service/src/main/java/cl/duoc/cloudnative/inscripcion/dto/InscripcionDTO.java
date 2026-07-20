package cl.duoc.cloudnative.inscripcion.dto;

import java.io.Serializable;

public class InscripcionDTO implements Serializable {
    private Long id;
    private Long estudianteId;
    private Long cursoId;
    private String estado;
    private String fechaInscripcion;
    private String nombreEstudiante;
    private String email;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getEstudianteId() { return estudianteId; }
    public void setEstudianteId(Long estudianteId) { this.estudianteId = estudianteId; }
    public Long getCursoId() { return cursoId; }
    public void setCursoId(Long cursoId) { this.cursoId = cursoId; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getFechaInscripcion() { return fechaInscripcion; }
    public void setFechaInscripcion(String fechaInscripcion) { this.fechaInscripcion = fechaInscripcion; }
    public String getNombreEstudiante() { return nombreEstudiante; }
    public void setNombreEstudiante(String nombreEstudiante) { this.nombreEstudiante = nombreEstudiante; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
