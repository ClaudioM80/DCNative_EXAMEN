package cl.duoc.cloudnative.inscripcion.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "inscripciones")
public class Inscripcion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String inscripcionId;
    private String cursoId;
    private String estudianteEmail;
    private LocalDateTime fechaInscripcion;
    private String estado;

    public Inscripcion() {}

    @PrePersist
    public void prePersist() {
        fechaInscripcion = LocalDateTime.now();
        estado = "CONFIRMADA";
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getInscripcionId() { return inscripcionId; }
    public void setInscripcionId(String inscripcionId) { this.inscripcionId = inscripcionId; }
    public String getCursoId() { return cursoId; }
    public void setCursoId(String cursoId) { this.cursoId = cursoId; }
    public String getEstudianteEmail() { return estudianteEmail; }
    public void setEstudianteEmail(String estudianteEmail) { this.estudianteEmail = estudianteEmail; }
    public LocalDateTime getFechaInscripcion() { return fechaInscripcion; }
    public void setFechaInscripcion(LocalDateTime fechaInscripcion) { this.fechaInscripcion = fechaInscripcion; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
