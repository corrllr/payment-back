package cvds.segundo.corte.parcial.repository;

import cvds.segundo.corte.parcial.model.Pago;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Pago repository interface for MongoDB
 */
public interface PagoRepository  extends MongoRepository<Pago, String> {

}
