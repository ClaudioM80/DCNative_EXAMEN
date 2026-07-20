package cl.duoc.cloudnative.gateway.producer;

import cl.duoc.cloudnative.gateway.dto.InscripcionDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InscripcionProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE = "exchange.inscripcion";
    private static final String ROUTING_KEY = "routing.inscripcion";

    public void enviarInscripcion(InscripcionDTO dto) {
        try {
            rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, dto);
            System.out.println("✅ Mensaje enviado a RabbitMQ: " + dto);
        } catch (Exception e) {
            System.err.println("❌ Error enviando mensaje: " + e.getMessage());
            throw new RuntimeException("Error al enviar mensaje a la cola", e);
        }
    }
}
