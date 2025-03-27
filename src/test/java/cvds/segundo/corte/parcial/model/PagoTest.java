package cvds.segundo.corte.parcial.model;

import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.*;

class PagoTest {

    @Test
    void createPagoWithValidData() {
        Map<String, Producto> products = new HashMap<>();
        products.put("1", new Producto("1", "Product1", 10.0, 1));
        Pago pago = new Pago("1", "user1", products, "2023-10-10", 100.0, "Aprobado");

        assertEquals("1", pago.getId());
        assertEquals("user1", pago.getIdUser());
        assertEquals(products, pago.getProducts());
        assertEquals("2023-10-10", pago.getDate());
        assertEquals(100.0, pago.getTotalPrice());
        assertEquals("Aprobado", pago.getStatus());
    }

    @Test
    void createPagoWithEmptyConstructor() {
        Pago pago = new Pago();

        assertNull(pago.getId());
        assertNull(pago.getIdUser());
        assertTrue(pago.getProducts().isEmpty());
        assertNull(pago.getDate());
        assertEquals(0.0, pago.getTotalPrice());
        assertNull(pago.getStatus());
    }

    @Test
    void setAndGetId() {
        Pago pago = new Pago();
        pago.setId("2");

        assertEquals("2", pago.getId());
    }

    @Test
    void setAndGetIdUser() {
        Pago pago = new Pago();
        pago.setIdUser("user2");

        assertEquals("user2", pago.getIdUser());
    }

    @Test
    void setAndGetProducts() {
        Pago pago = new Pago();
        Map<String, Producto> products = new HashMap<>();
        products.put("2", new Producto("2", "Product2", 20.0, 2));
        pago.setProducts(products);

        assertEquals(products, pago.getProducts());
    }

    @Test
    void setAndGetDate() {
        Pago pago = new Pago();
        pago.setDate("2023-11-11");

        assertEquals("2023-11-11", pago.getDate());
    }

    @Test
    void setAndGetTotalPrice() {
        Pago pago = new Pago();
        pago.setTotalPrice(200.0);

        assertEquals(200.0, pago.getTotalPrice());
    }

    @Test
    void setAndGetStatus() {
        Pago pago = new Pago();
        pago.setStatus("Declinado");

        assertEquals("Declinado", pago.getStatus());
    }
}