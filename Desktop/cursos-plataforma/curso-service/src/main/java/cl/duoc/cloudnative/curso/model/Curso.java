package cl.duoc.cloudnative.curso.model;

public class Curso {
    private String cursoId;
    private String nombre;
    private String descripcion;
    private String instructor;
    private int cupos;

    public Curso() {}

    public Curso(String cursoId, String nombre, String descripcion, String instructor, int cupos) {
        this.cursoId = cursoId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.instructor = instructor;
        this.cupos = cupos;
    }

    public String getCursoId() { return cursoId; }
    public void setCursoId(String cursoId) { this.cursoId = cursoId; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public String getInstructor() { return instructor; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
    public int getCupos() { return cupos; }
    public void setCupos(int cupos) { this.cupos = cupos; }
}