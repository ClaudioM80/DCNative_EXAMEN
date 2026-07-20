package cl.duoc.cloudnative.inscripcion.consumer;

import cl.duoc.cloudnative.inscripcion.dto.InscripcionDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class InscripcionConsumer {

    @RabbitListener(queues = "queue.inscripcion")
    public void procesarInscripcion(InscripcionDTO dto) {
        System.out.println("📩 Mensaje recibido en consumidor:");
        System.out.println("   Estudiante ID: " + dto.getEstudianteId());
        System.out.println("   Curso ID: " + dto.getCursoId());
        System.out.println("   Nombre: " + dto.getNombreEstudiante());
        System.out.println("   Email: " + dto.getEmail());
        System.out.println("   Estado: " + dto.getEstado());
        System.out.println("✅ Inscripción procesada exitosamente");
    }
}
