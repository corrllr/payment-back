package cvds.segundo.corte.parcial.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProductoTest {

    @Test
    void createProductoWithValidData() {
        Producto producto = new Producto("1", "Product1", 10.0, 5);

        assertEquals("1", producto.getId());
        assertEquals("Product1", producto.getName());
        assertEquals(10.0, producto.getUnitPrice());
        assertEquals(5, producto.getUnits());
    }

    @Test
    void createProductoWithEmptyConstructor() {
        Producto producto = new Producto();

        assertNull(producto.getId());
        assertNull(producto.getName());
        assertEquals(0.0, producto.getUnitPrice());
        assertEquals(0, producto.getUnits());
    }

    @Test
    void setAndGetId() {
        Producto producto = new Producto();
        producto.setId("2");

        assertEquals("2", producto.getId());
    }

    @Test
    void setAndGetName() {
        Producto producto = new Producto();
        producto.setName("Product2");

        assertEquals("Product2", producto.getName());
    }

    @Test
    void setAndGetUnitPrice() {
        Producto producto = new Producto();
        producto.setUnitPrice(20.0);

        assertEquals(20.0, producto.getUnitPrice());
    }

    @Test
    void setAndGetUnits() {
        Producto producto = new Producto();
        producto.setUnits(10);

        assertEquals(10, producto.getUnits());
    }
}