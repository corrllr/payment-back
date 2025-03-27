package cvds.segundo.corte.parcial.controller;

import cvds.segundo.corte.parcial.model.Pago;
import cvds.segundo.corte.parcial.model.Producto;
import cvds.segundo.corte.parcial.service.InvalidTotalException;
import cvds.segundo.corte.parcial.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador de pagos
 */
@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    private PagoService pagoService;

    /**
     * Constructor de PagoController
     * @param pagoService Servicio de pagos
     */
    @Autowired
    public PagoController(PagoService pagoService) {
        this.pagoService = pagoService;
    }

    /**
     * Endpoint para crear un pago
     * @param pago El pago a crear
     * @param totalUsuario Total dado por el usuario
     * @return ResponseEntity con el pago creado
     */
    @PostMapping("/{totalUsuario}")
    public ResponseEntity<Pago> crearPago(@RequestBody Pago pago, @PathVariable("totalUsuario") double totalUsuario) {
        try {
            Pago nuevoPago = pagoService.crearPago(pago, totalUsuario);
            return new ResponseEntity<>(nuevoPago, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint para obtener todos los pagos de un usuario
     * @param idUser ID del usuario
     * @return Lista de pagos del usuario
     */
    @GetMapping("/{idUser}")
    public ResponseEntity<List<Pago>> obtenerPagosPorUsuario(@PathVariable("idUser") String idUser) {
        try {
            List<Pago> pagos = pagoService.obtenerPagosPorUsuario(idUser);
            return new ResponseEntity<>(pagos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Endpoint para añadir un producto a un pago
     * @param idPago ID del pago
     * @param producto El producto a añadir
     * @return El pago actualizado
     */
    @PutMapping("/{idPago}/producto")
    public ResponseEntity<Pago> aniadirProductoAPago(@PathVariable("idPago") String idPago, @RequestBody Producto producto) {
        try {
            Pago pagoActualizado = pagoService.aniadirProductoAPago(idPago, producto);
            return new ResponseEntity<>(pagoActualizado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Endpoint para validar que el total dado por el usuario coincida con el calculado por el sistema
     * @param idPago ID del pago
     * @param totalUsuario Total dado por el usuario
     * @return El estado del pago
     */
    @PutMapping("/{idPago}/total")
    public ResponseEntity<String> validarTotal(@PathVariable String idPago, @RequestParam double totalUsuario) {
        try {
            String estado = pagoService.validarTotal(idPago, totalUsuario);
            return new ResponseEntity<>(estado, HttpStatus.OK);
        } catch (InvalidTotalException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Pago no encontrado", HttpStatus.NOT_FOUND);
        }
    }

}
