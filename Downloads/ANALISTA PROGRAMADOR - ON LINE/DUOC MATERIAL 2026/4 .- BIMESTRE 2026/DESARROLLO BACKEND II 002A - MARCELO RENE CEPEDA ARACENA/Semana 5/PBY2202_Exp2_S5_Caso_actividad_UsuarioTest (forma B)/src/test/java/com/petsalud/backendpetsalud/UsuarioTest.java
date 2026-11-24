package com.petsalud.backendpetsalud;

import com.petsalud.backendpetsalud.entity.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    // --- TESTS DEL ARCHIVO 1 (Validación de Builder y Equals) ---
    
    @Test
    public void testCrearUsuario() {
        Usuario usuario = Usuario.builder()
                .email("juan.perez@example.com")
                .password("password123")
                .nombre("Juan")
                .apellido("Perez")
                .telefono("1234567890")
                .direccion("Calle Falsa 123")
                .rol(Rol.CLIENTE)
                .mascotas(new ArrayList<>())
                .ventas(new ArrayList<>())
                .recompensas(new ArrayList<>())
                .notificaciones(new ArrayList<>())
                .build();

        assertNotNull(usuario);
        assertEquals("juan.perez@example.com", usuario.getEmail());
        assertEquals("password123", usuario.getPassword());
        assertEquals("Juan", usuario.getNombre());
        assertEquals("Perez", usuario.getApellido());
        assertEquals("1234567890", usuario.getTelefono());
        assertEquals("Calle Falsa 123", usuario.getDireccion());
        assertEquals(Rol.CLIENTE, usuario.getRol());
        assertTrue(usuario.getMascotas().isEmpty());
        assertTrue(usuario.getVentas().isEmpty());
        assertTrue(usuario.getRecompensas().isEmpty());
        assertTrue(usuario.getNotificaciones().isEmpty());
    }

    @Test
    public void testEquals() {
        List<Mascota> mascotas = new ArrayList<>();
        List<Venta> ventas = new ArrayList<>();
        List<Recompensa> recompensas = new ArrayList<>();
        List<Notificacion> notificaciones = new ArrayList<>();

        Usuario usuario1 = Usuario.builder()
                .id(1L).email("juan.perez@example.com").password("password123")
                .nombre("Juan").apellido("Perez").telefono("1234567890")
                .direccion("Calle Falsa 123").rol(Rol.CLIENTE)
                .mascotas(mascotas).ventas(ventas).recompensas(recompensas).notificaciones(notificaciones)
                .build();

        Usuario usuario2 = Usuario.builder()
                .id(1L).email("juan.perez@example.com").password("password123")
                .nombre("Juan").apellido("Perez").telefono("1234567890")
                .direccion("Calle Falsa 123").rol(Rol.CLIENTE)
                .mascotas(mascotas).ventas(ventas).recompensas(recompensas).notificaciones(notificaciones)
                .build();

        assertEquals(usuario1, usuario2);
    }

    // --- TESTS DEL ARCHIVO 2 (Requerimientos exactos de la guía) ---

    @Test
    void testCamposObligatoriosNoNulos() {
        Usuario usuario = Usuario.builder()
                .email("test@test.cl")
                .nombre("Juan")
                .rol(Rol.CLIENTE)
                .build();

        assertNotNull(usuario.getEmail(), "El email no debe ser nulo");
        assertFalse(usuario.getEmail().isEmpty(), "El email no debe estar vacío");
        assertNotNull(usuario.getNombre(), "El nombre no debe ser nulo");
        assertNotNull(usuario.getRol(), "El rol no debe ser nulo");
    }

    @Test
    void testRolValido() {
        Usuario usuario = Usuario.builder().rol(Rol.VETERINARIO).build();
        // Verificamos que el rol sea uno de los permitidos
        boolean rolValido = usuario.getRol() == Rol.CLIENTE || 
                            usuario.getRol() == Rol.VETERINARIO || 
                            usuario.getRol() == Rol.ADMIN;
        assertTrue(rolValido, "El rol debe ser CLIENTE, VETERINARIO o ADMIN");
    }

    @Test
    void testRelacionUsuarioMascotaValida() {
        Mascota mascota = Mascota.builder()
                .nombre("Firulais")
                .especie("Perro")
                .build();
        
        List<Mascota> mascotas = new ArrayList<>();
        mascotas.add(mascota);

        Usuario cliente = Usuario.builder()
                .email("cliente@test.cl")
                .rol(Rol.CLIENTE)
                .mascotas(mascotas)
                .build();

        assertNotNull(cliente.getMascotas(), "La lista de mascotas no debe ser nula");
        assertEquals(1, cliente.getMascotas().size(), "El cliente debe tener 1 mascota");
        assertEquals("Firulais", cliente.getMascotas().get(0).getNombre());
        assertEquals("Perro", cliente.getMascotas().get(0).getEspecie());
    }
}