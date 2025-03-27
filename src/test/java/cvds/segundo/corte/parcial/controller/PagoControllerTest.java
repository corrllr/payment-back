package cvds.segundo.corte.parcial.controller;

import cvds.segundo.corte.parcial.model.Pago;
import cvds.segundo.corte.parcial.model.Producto;
import cvds.segundo.corte.parcial.service.InvalidTotalException;
import cvds.segundo.corte.parcial.service.PagoService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PagoControllerTest {

    @Test
    void crearPagoWithValidData() {
        PagoService pagoService = mock(PagoService.class);
        PagoController pagoController = new PagoController(pagoService);

        Map<String, Producto> products = new HashMap<>();
        products.put("1", new Producto("1", "Product1", 10.0, 2));
        Pago pago = new Pago("1", "user1", products, "2023-10-10", 0.0, null);

        when(pagoService.crearPago(any(Pago.class), eq(20.0))).thenReturn(new Pago("1", "user1", products, "2023-10-10", 20.0, "Aprobado"));

        ResponseEntity<Pago> response = pagoController.crearPago(pago, 20.0);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(20.0, response.getBody().getTotalPrice());
        assertEquals("Aprobado", response.getBody().getStatus());
    }

    @Test
    void crearPagoWithInvalidData() {
        PagoService pagoService = mock(PagoService.class);
        PagoController pagoController = new PagoController(pagoService);

        Map<String, Producto> products = new HashMap<>();
        products.put("1", new Producto("1", "Product1", 10.0, 2));
        Pago pago = new Pago("1", "user1", products, "2023-10-10", 0.0, null);

        when(pagoService.crearPago(any(Pago.class), eq(20.0))).thenThrow(new RuntimeException());

        ResponseEntity<Pago> response = pagoController.crearPago(pago, 20.0);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void obtenerPagosPorUsuarioWithExistingUser() {
        PagoService pagoService = mock(PagoService.class);
        PagoController pagoController = new PagoController(pagoService);

        Pago pago1 = new Pago("1", "user1", new HashMap<>(), "2023-10-10", 100.0, "Aprobado");
        Pago pago2 = new Pago("2", "user1", new HashMap<>(), "2023-10-11", 200.0, "Aprobado");

        when(pagoService.obtenerPagosPorUsuario("user1")).thenReturn(List.of(pago1, pago2));

        ResponseEntity<List<Pago>> response = pagoController.obtenerPagosPorUsuario("user1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertTrue(response.getBody().contains(pago1));
        assertTrue(response.getBody().contains(pago2));
    }

    @Test
    void obtenerPagosPorUsuarioWithNonExistingUser() {
        PagoService pagoService = mock(PagoService.class);
        PagoController pagoController = new PagoController(pagoService);

        when(pagoService.obtenerPagosPorUsuario("user2")).thenReturn(List.of());

        ResponseEntity<List<Pago>> response = pagoController.obtenerPagosPorUsuario("user2");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    void aniadirProductoAPagoWithValidData() {
        PagoService pagoService = mock(PagoService.class);
        PagoController pagoController = new PagoController(pagoService);

        Map<String, Producto> products = new HashMap<>();
        products.put("1", new Producto("1", "Product1", 10.0, 2));

        Producto newProduct = new Producto("2", "Product2", 15.0, 1);
        when(pagoService.aniadirProductoAPago("1", newProduct)).thenReturn(new Pago("1", "user1", products, "2023-10-10", 35.0, "Aprobado"));

        ResponseEntity<Pago> response = pagoController.aniadirProductoAPago("1", newProduct);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(35.0, response.getBody().getTotalPrice());
    }

    @Test
    void validarTotalWithValidTotal() {
        PagoService pagoService = mock(PagoService.class);
        PagoController pagoController = new PagoController(pagoService);

        when(pagoService.validarTotal("1", 20.0)).thenReturn("Aprobado");

        ResponseEntity<String> response = pagoController.validarTotal("1", 20.0);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Aprobado", response.getBody());
    }

    @Test
    void validarTotalWithInvalidTotal() {
        PagoService pagoService = mock(PagoService.class);
        PagoController pagoController = new PagoController(pagoService);

        when(pagoService.validarTotal("1", 15.0)).thenThrow(new InvalidTotalException("Total inválido"));

        ResponseEntity<String> response = pagoController.validarTotal("1", 15.0);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Total inválido", response.getBody());
    }
}