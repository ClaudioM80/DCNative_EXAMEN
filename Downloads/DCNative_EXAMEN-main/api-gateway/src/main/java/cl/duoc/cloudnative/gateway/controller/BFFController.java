package cl.duoc.cloudnative.gateway.controller;

import cl.duoc.cloudnative.gateway.dto.InscripcionDTO;
import cl.duoc.cloudnative.gateway.producer.InscripcionProducer;
import cl.duoc.cloudnative.gateway.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/bff")
@CrossOrigin(origins = "*")
public class BFFController {

    @Autowired
    private InscripcionProducer inscripcionProducer;

    @Autowired
    private StorageService storageService;

    @PostMapping("/inscribir")
    public ResponseEntity<Map<String, Object>> inscribirEstudiante(@RequestBody InscripcionDTO dto) {
        Map<String, Object> response = new HashMap<>();
        
        try {
            if (dto.getEstudianteId() == null || dto.getCursoId() == null) {
                response.put("error", "Faltan datos obligatorios");
                return ResponseEntity.badRequest().body(response);
            }

            dto.setEstado("PENDIENTE");
            inscripcionProducer.enviarInscripcion(dto);

            response.put("mensaje", "✅ Solicitud enviada a la cola");
            response.put("estudianteId", dto.getEstudianteId());
            response.put("cursoId", dto.getCursoId());
            response.put("estado", "PENDIENTE");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            response.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/estado/{estudianteId}")
    public ResponseEntity<Map<String, Object>> consultarEstado(@PathVariable Long estudianteId) {
        Map<String, Object> response = new HashMap<>();
        response.put("estudianteId", estudianteId);
        response.put("estado", "PROCESADO");
        response.put("mensaje", "Inscripción completada exitosamente");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/upload")
    public ResponseEntity<Map<String, Object>> uploadArchivo(@RequestParam("file") MultipartFile file) {
        Map<String, Object> response = new HashMap<>();
        try {
            String url = storageService.uploadFile(file);
            response.put("mensaje", "✅ Archivo subido exitosamente");
            response.put("url", url);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IOException e) {
            response.put("error", e.getMessage());
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @GetMapping("/health")
    public ResponseEntity<Map<String, Object>> healthCheck() {
        Map<String, Object> response = new HashMap<>();
        response.put("estado", "✅ BFF funcionando correctamente");
        response.put("servicio", "API Gateway BFF");
        response.put("version", "1.0.0");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("status", "UP");
        return ResponseEntity.ok(response);
    }
}
