package cvds.segundo.corte.parcial.service;

import cvds.segundo.corte.parcial.model.Pago;
import cvds.segundo.corte.parcial.model.Producto;
import cvds.segundo.corte.parcial.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Pago service class for payment operations
 */
@Service
public class PagoService {

    private PagoRepository pagoRepository;

    /**
     * Constructor
     * @param pagoRepository
     */
    @Autowired
    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    /**
     * Create a new payment
     * @param pago Pago
     * @param totalUser double
     * @return Pago
     */
    public Pago crearPago(Pago pago, double totalUser){
        double total = calcularTotal(pago.getProducts());
        pago.setTotalPrice(total);
        pago.setStatus(calcularEstado(pago.getTotalPrice(), totalUser));
        return pagoRepository.save(pago);
    }

    private double calcularTotal(Map<String, Producto> products){
        return products.values().stream()
                .mapToDouble(producto -> producto.getUnitPrice() * producto.getUnits())
                .sum();
    }

    private String calcularEstado(double total, double totalUser){
        return total == totalUser ? "Aprobado" : "Declinado";
    }

    /**
     * Get all payments by user id
     * @param userId
     * @return
     */
    public List<Pago> obtenerPagosPorUsuario(String userId){
        return pagoRepository.findAll().stream()
                .filter(pago -> pago.getIdUser().equals(userId))
                .collect(Collectors.toList());
    }

    /**
     * Add product to payment
     * @param idPago String
     * @param producto Producto
     * @return Pago
     */
    public Pago aniadirProductoAPago(String idPago, Producto producto){
        producto.setId(generateProductId());
        Pago pago = pagoRepository.findById(idPago).orElseThrow(() -> new RuntimeException("Pago no encontrado"));
        pago.getProducts().put(producto.getId(), producto);
        double total = calcularTotal(pago.getProducts());
        pago.setTotalPrice(total);
        return pagoRepository.save(pago);
    }

    /**
     * Validar que el total dado por el usuario coincida con el calculado por el sistema
     * @param idPago ID del pago
     * @param totalUsuario Total dado por el usuario
     * @return El estado del pago
     */
    public String validarTotal(String idPago, double totalUsuario) {
        Pago pago = pagoRepository.findById(idPago).orElseThrow(() -> new RuntimeException("Pago no encontrado"));
        if (pago.getTotalPrice() != totalUsuario) {
            pago.setStatus("Declinado");
            throw new InvalidTotalException("El total dado por el usuario no coincide con el total calculado por el sistema");
        }else{
            pago.setStatus("Aprobado");
        }

        return pagoRepository.save(pago).getStatus();
    }

    /**
     * Generar id del producto aleatoriamente y automaticamente con UUID
     */
    private String generateProductId(){
        return UUID.randomUUID().toString();
    }



}
