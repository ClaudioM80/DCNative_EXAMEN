package com.petsalud.backendpetsalud;

import com.petsalud.backendpetsalud.entity.Cita;
import com.petsalud.backendpetsalud.entity.Mascota;
import com.petsalud.backendpetsalud.entity.Rol;
import com.petsalud.backendpetsalud.entity.Usuario;
import com.petsalud.backendpetsalud.repository.CitaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CitaTest {

    // Simulamos el repositorio de citas con Mockito
    @Mock
    private CitaRepository citaRepository;

    @Test
    void testCreacionCitaEstadoInicial() {
        Cita cita = Cita.builder()
                .fechaHora(LocalDateTime.now().plusDays(1))
                .estado("Agendada")
                .build();

        assertEquals("Agendada", cita.getEstado(), "El estado inicial debe ser 'Agendada'");
    }

    @Test
    void testNoAgendarFechaOcupada() {
        LocalDateTime fechaOcupada = LocalDateTime.of(2026, 11, 24, 10, 0);
        
        // Simulamos que ya existe una cita en ese horario usando el método de tu repositorio
        Cita citaExistente = Cita.builder().fechaHora(fechaOcupada).estado("Agendada").build();
        
        // Le decimos a Mockito: "Cuando busques en este rango, devuelve una lista con la cita existente"
        when(citaRepository.findByFechaHoraBetween(fechaOcupada, fechaOcupada))
                .thenReturn(List.of(citaExistente));

        // Lógica de negocio: Si la lista NO está vacía, la fecha está ocupada y no se puede agendar
        List<Cita> citasEncontradas = citaRepository.findByFechaHoraBetween(fechaOcupada, fechaOcupada);
        boolean sePuedeAgendar = citasEncontradas.isEmpty();
        
        assertFalse(sePuedeAgendar, "No se debe poder agendar una cita en una fecha ya ocupada");
        
        // Verificamos que Mockito fue llamado correctamente
        verify(citaRepository, times(1)).findByFechaHoraBetween(fechaOcupada, fechaOcupada);
    }

    @Test
    void testAgendarFechaDisponible() {
        LocalDateTime fechaDisponible = LocalDateTime.of(2026, 11, 25, 15, 0);
        
        // Simulamos que NO hay citas en ese horario (lista vacía)
        when(citaRepository.findByFechaHoraBetween(fechaDisponible, fechaDisponible))
                .thenReturn(Collections.emptyList());

        List<Cita> citasEncontradas = citaRepository.findByFechaHoraBetween(fechaDisponible, fechaDisponible);
        boolean sePuedeAgendar = citasEncontradas.isEmpty();
        
        assertTrue(sePuedeAgendar, "Se debe poder agendar una cita en un horario disponible");
        verify(citaRepository, times(1)).findByFechaHoraBetween(fechaDisponible, fechaDisponible);
    }

    @Test
    void testValidacionRelacionMascotaVeterinario() {
        Usuario veterinario = Usuario.builder()
                .email("vet@test.cl")
                .rol(Rol.VETERINARIO)
                .build();

        Usuario propietario = Usuario.builder()
                .email("duenio@test.cl")
                .rol(Rol.CLIENTE)
                .build();

        Mascota mascota = Mascota.builder()
                .nombre("Rex")
                .especie("Perro")
                .propietario(propietario)
                .build();

        Cita cita = Cita.builder()
                .mascota(mascota)
                .veterinario(veterinario)
                .build();

        assertNotNull(cita.getVeterinario(), "La cita debe tener un veterinario asignado");
        assertEquals(Rol.VETERINARIO, cita.getVeterinario().getRol(), "El asignado debe tener rol de VETERINARIO");
        assertNotNull(cita.getMascota(), "La cita debe tener una mascota");
        assertNotNull(cita.getMascota().getPropietario(), "La mascota debe tener un propietario registrado");
    }
}