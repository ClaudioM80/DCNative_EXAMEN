package cl.duoc.cloudnative.consumer.listener;

import cl.duoc.cloudnative.consumer.model.Guia;
import cl.duoc.cloudnative.consumer.model.GuiaFallida;
import cl.duoc.cloudnative.consumer.model.PedidoCreadoEvent;
import cl.duoc.cloudnative.consumer.repository.GuiaFallidaRepository;
import cl.duoc.cloudnative.consumer.repository.GuiaRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoConsumerListener {

    private final GuiaRepository guiaRepository;
    private final GuiaFallidaRepository guiaFallidaRepository;

    public PedidoConsumerListener(GuiaRepository guiaRepository, GuiaFallidaRepository guiaFallidaRepository) {
        this.guiaRepository = guiaRepository;
        this.guiaFallidaRepository = guiaFallidaRepository;
    }

    @RabbitListener(queues = "${app.rabbitmq.queue}")
    public void procesarPedido(PedidoCreadoEvent event) {
        System.out.println("📨 Mensaje recibido: " + event.getPedidoId());

        try {
            if (event.getProducto() != null && event.getProducto().equalsIgnoreCase("ERROR")) {
                throw new IllegalArgumentException("Error simulado");
            }

            Guia guia = new Guia();
            guia.setPedidoId(event.getPedidoId());
            guia.setProducto(event.getProducto());
            guia.setCantidad(event.getCantidad());
            guia.setEmail(event.getEmail());
            guia.setRoles(event.getRoles());
            guiaRepository.save(guia);
            
            System.out.println("✅ Pedido " + event.getPedidoId() + " procesado correctamente");
            
        } catch (Exception e) {
            System.err.println("❌ Error procesando pedido " + event.getPedidoId() + ": " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @RabbitListener(queues = "${app.rabbitmq.dlq}")
    public void procesarPedidoFallido(PedidoCreadoEvent event) {
        System.out.println("⚠️ Mensaje fallido recibido en DLQ: " + event.getPedidoId());
        
        GuiaFallida fallida = new GuiaFallida();
        fallida.setPedidoId(event.getPedidoId());
        fallida.setProducto(event.getProducto());
        fallida.setCantidad(event.getCantidad());
        fallida.setEmail(event.getEmail());
        fallida.setRoles(event.getRoles());
        fallida.setMotivoFallo("Error en cola principal");
        guiaFallidaRepository.save(fallida);
        
        System.out.println("📝 Pedido fallido " + event.getPedidoId() + " guardado en tabla de fallidos");
    }
}
