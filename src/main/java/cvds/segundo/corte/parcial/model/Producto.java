package cvds.segundo.corte.parcial.model;

import org.springframework.data.annotation.Id;

/**
 * Product model class
 */
public class Producto {
    @Id
    private String id;
    private String name;
    private double unitPrice;
    private int units;

    /**
     * Constructor for Product with parameters
     * @param id
     * @param name
     * @param unitPrice
     * @param units
     */
    public Producto(String id, String name, double unitPrice, int units) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.units = units;
    }

    /**
     * Empty constructor for Product
     */
    public Producto() {
    }

    /**
     * Get the product id
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * Set the product id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Get the product name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the product name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the product unit price
     * @return unitPrice
     */
    public double getUnitPrice() {
        return unitPrice;
    }

    /**
     * Set the product unit price
     * @param unitPrice
     */
    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * Get the product units
     * @return units
     */
    public int getUnits() {
        return units;
    }

    /**
     * Set the product units
     * @param units
     */
    public void setUnits(int units) {
        this.units = units;
    }


}
