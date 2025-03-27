package cvds.segundo.corte.parcial.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * Pago model class
 */
@Document(collection = "cvds")
public class Pago {

    @Id
    private String id;
    private String idUser;
    private Map<String,Producto> products = new HashMap<>();
    private String date;
    private double totalPrice;
    private String status; //Aprobado o Declinado

    /**
     * Constructor for Pago
     * @param id
     * @param idUser
     * @param products
     * @param date
     * @param totalPrice
     * @param status
     */
    public Pago(String id, String idUser, Map<String, Producto> products, String date, double totalPrice, String status) {
        this.id = id;
        this.idUser = idUser;
        this.products = products;
        this.date = date;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    /**
     * Empty constructor for Pago
     */
    public Pago(){

    }

    /**
     * Get the id
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the idUser
     * @return idUser
     */
    public String getIdUser() {
        return idUser;
    }

    /**
     * Set the idUser
     * @param idUser
     */
    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    /**
     * Get the products
     * @return
     */
    public Map<String, Producto> getProducts() {
        return products;
    }

    /**
     * Set the products
     * @param products
     */
    public void setProducts(Map<String, Producto> products) {
        this.products = products;
    }

    /**
     * Get the date
     * @return date
     */
    public String getDate() {
        return date;
    }

    /**
     * Set the date
     * @param date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Get the total price
     * @return totalPrice
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Set the total price
     * @param totalPrice
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Get the status
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set the status
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
