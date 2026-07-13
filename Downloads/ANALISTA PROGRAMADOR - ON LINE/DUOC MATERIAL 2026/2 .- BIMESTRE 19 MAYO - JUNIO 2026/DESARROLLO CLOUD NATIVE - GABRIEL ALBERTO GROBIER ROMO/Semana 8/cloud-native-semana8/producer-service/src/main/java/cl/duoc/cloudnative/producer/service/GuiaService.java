package cl.duoc.cloudnative.producer.service;

import cl.duoc.cloudnative.producer.model.GuiaRequest;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class GuiaService {
    
    public String crearGuia(GuiaRequest request) {
        String guiaId = "G-" + UUID.randomUUID().toString().substring(0, 8);
        System.out.println("📦 Guía creada: " + guiaId + " para producto: " + request.getProducto());
        return guiaId;
    }
}

