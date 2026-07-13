package cl.duoc.cloudnative.producer.controller;

import cl.duoc.cloudnative.producer.model.GuiaRequest;
import cl.duoc.cloudnative.producer.model.PedidoCreadoEvent;
import cl.duoc.cloudnative.producer.service.GuiaService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/guias")
public class GuiaController {

    private final GuiaService guiaService;
    private final RabbitTemplate rabbitTemplate;

    @Value("${app.rabbitmq.exchange}")
    private String exchange;

    @Value("${app.rabbitmq.routing-key}")
    private String routingKey;

    // Constructor con parámetros (Spring lo usará para inyectar las dependencias)
    public GuiaController(GuiaService guiaService, RabbitTemplate rabbitTemplate) {
        this.guiaService = guiaService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @PostMapping
    public ResponseEntity<String> crearGuia(@RequestBody GuiaRequest request) {
        try {
            String guiaId = guiaService.crearGuia(request);
            
            PedidoCreadoEvent event = new PedidoCreadoEvent(
                UUID.randomUUID().toString(),
                guiaId,
                request.getUsuarioId(),
                request.getProducto(),
                request.getCantidad(),
                request.getEmail(),
                request.getRoles()
            );
            
            rabbitTemplate.convertAndSend(exchange, routingKey, event);
            
            return ResponseEntity.ok("Guía creada: " + guiaId);
            
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}