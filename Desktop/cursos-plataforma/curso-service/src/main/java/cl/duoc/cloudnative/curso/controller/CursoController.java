package cl.duoc.cloudnative.curso.controller;

import cl.duoc.cloudnative.curso.model.Curso;
import cl.duoc.cloudnative.curso.model.Inscripcion;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final RabbitTemplate rabbitTemplate;
    private final List<Curso> cursos = new ArrayList<>();

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.routing-key}")
    private String routingKey;

    public CursoController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        cursos.add(new Curso("CUR-001", "Java Avanzado", "Curso de Java para profesionales", "Dr. Silva", 20));
        cursos.add(new Curso("CUR-002", "Spring Boot", "Desarrollo de APIs con Spring", "Mg. Pérez", 15));
        cursos.add(new Curso("CUR-003", "Cloud Native", "Arquitectura en la nube", "Ing. Gómez", 10));
    }

    @GetMapping
    public List<Curso> listarCursos() {
        return cursos;
    }

    @PostMapping("/inscribir")
    public String inscribirEstudiante(@RequestBody Inscripcion inscripcion) {
        rabbitTemplate.convertAndSend(exchange, routingKey, inscripcion);
        return "✅ Inscripción enviada: " + inscripcion.getInscripcionId() + 
               " para el curso " + inscripcion.getCursoId();
    }
}