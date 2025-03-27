package cvds.segundo.corte.parcial.service;

import cvds.segundo.corte.parcial.model.Pago;
import cvds.segundo.corte.parcial.model.Producto;
import cvds.segundo.corte.parcial.repository.PagoRepository;
import org.junit.jupiter.api.Test;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PagoServiceTest {

    @Test
    void crearPagoWithValidData() {
        PagoRepository pagoRepository = mock(PagoRepository.class);
        PagoService pagoService = new PagoService(pagoRepository);

        Map<String, Producto> products = new HashMap<>();
        products.put("1", new Producto("1", "Product1", 10.0, 2));
        Pago pago = new Pago("1", "user1", products, "2023-10-10", 0.0, null);

        when(pagoRepository.save(any(Pago.class))).thenReturn(pago);

        Pago result = pagoService.crearPago(pago, 20.0);

        assertEquals(20.0, result.getTotalPrice());
        assertEquals("Aprobado", result.getStatus());
    }

    @Test
    void crearPagoWithInvalidTotal() {
        PagoRepository pagoRepository = mock(PagoRepository.class);
        PagoService pagoService = new PagoService(pagoRepository);

        Map<String, Producto> products = new HashMap<>();
        products.put("1", new Producto("1", "Product1", 10.0, 2));
        Pago pago = new Pago("1", "user1", products, "2023-10-10", 0.0, null);

        when(pagoRepository.save(any(Pago.class))).thenReturn(pago);

        Pago result = pagoService.crearPago(pago, 15.0);

        assertEquals(20.0, result.getTotalPrice());
        assertEquals("Declinado", result.getStatus());
    }

    @Test
    void obtenerPagosPorUsuarioWithExistingUser() {
        PagoRepository pagoRepository = mock(PagoRepository.class);
        PagoService pagoService = new PagoService(pagoRepository);

        Pago pago1 = new Pago("1", "user1", new HashMap<>(), "2023-10-10", 100.0, "Aprobado");
        Pago pago2 = new Pago("2", "user1", new HashMap<>(), "2023-10-11", 200.0, "Aprobado");

        when(pagoRepository.findAll()).thenReturn(List.of(pago1, pago2));

        List<Pago> result = pagoService.obtenerPagosPorUsuario("user1");

        assertEquals(2, result.size());
        assertTrue(result.contains(pago1));
        assertTrue(result.contains(pago2));
    }

    @Test
    void obtenerPagosPorUsuarioWithNonExistingUser() {
        PagoRepository pagoRepository = mock(PagoRepository.class);
        PagoService pagoService = new PagoService(pagoRepository);

        Pago pago1 = new Pago("1", "user1", new HashMap<>(), "2023-10-10", 100.0, "Aprobado");
        Pago pago2 = new Pago("2", "user1", new HashMap<>(), "2023-10-11", 200.0, "Aprobado");

        when(pagoRepository.findAll()).thenReturn(List.of(pago1, pago2));

        List<Pago> result = pagoService.obtenerPagosPorUsuario("user2");

        assertTrue(result.isEmpty());
    }

    @Test
    void validarTotalWithInvalidTotal() {
        PagoRepository pagoRepository = mock(PagoRepository.class);
        PagoService pagoService = new PagoService(pagoRepository);

        Map<String, Producto> products = new HashMap<>();
        products.put("1", new Producto("1", "Product1", 10.0, 2));
        Pago pago = new Pago("1", "user1", products, "2023-10-10", 20.0, "Aprobado");

        when(pagoRepository.findById("1")).thenReturn(Optional.of(pago));

        assertThrows(InvalidTotalException.class, () -> {
            pagoService.validarTotal("1", 15.0);
        });
    }
}
