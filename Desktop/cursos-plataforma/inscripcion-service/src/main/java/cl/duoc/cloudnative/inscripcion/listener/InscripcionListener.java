package cl.duoc.cloudnative.inscripcion.listener;

import cl.duoc.cloudnative.inscripcion.model.Inscripcion;
import cl.duoc.cloudnative.inscripcion.model.InscripcionEvent;
import cl.duoc.cloudnative.inscripcion.repository.InscripcionRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class InscripcionListener {

    private final InscripcionRepository repository;

    public InscripcionListener(InscripcionRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void procesarInscripcion(InscripcionEvent event) {
        System.out.println("📧 NUEVA INSCRIPCIÓN RECIBIDA:");
        System.out.println("ID: " + event.getInscripcionId());
        System.out.println("Curso: " + event.getCursoId());
        System.out.println("Estudiante: " + event.getEstudianteEmail());
        System.out.println("Fecha: " + event.getFechaInscripcion());

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setInscripcionId(event.getInscripcionId());
        inscripcion.setCursoId(event.getCursoId());
        inscripcion.setEstudianteEmail(event.getEstudianteEmail());
        inscripcion.setEstado("CONFIRMADA");

        repository.save(inscripcion);
        System.out.println("✅ Inscripción guardada en BD exitosamente");
    }
}