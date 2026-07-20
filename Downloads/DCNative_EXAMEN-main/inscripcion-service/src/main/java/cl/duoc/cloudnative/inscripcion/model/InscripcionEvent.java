package cl.duoc.cloudnative.inscripcion.model;

public class InscripcionEvent {
    private String inscripcionId;
    private String cursoId;
    private String estudianteEmail;
    private String fechaInscripcion;

    public InscripcionEvent() {}

    public InscripcionEvent(String inscripcionId, String cursoId, String estudianteEmail, String fechaInscripcion) {
        this.inscripcionId = inscripcionId;
        this.cursoId = cursoId;
        this.estudianteEmail = estudianteEmail;
        this.fechaInscripcion = fechaInscripcion;
    }

    // Getters y Setters
    public String getInscripcionId() { return inscripcionId; }
    public void setInscripcionId(String inscripcionId) { this.inscripcionId = inscripcionId; }
    public String getCursoId() { return cursoId; }
    public void setCursoId(String cursoId) { this.cursoId = cursoId; }
    public String getEstudianteEmail() { return estudianteEmail; }
    public void setEstudianteEmail(String estudianteEmail) { this.estudianteEmail = estudianteEmail; }
    public String getFechaInscripcion() { return fechaInscripcion; }
    public void setFechaInscripcion(String fechaInscripcion) { this.fechaInscripcion = fechaInscripcion; }
}
