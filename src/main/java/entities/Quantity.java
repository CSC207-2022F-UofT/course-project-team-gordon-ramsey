package entities;

import java.io.Serializable;

/**
 * represents a quantity with a unit.
 */
public class Quantity implements Serializable{

    /**
     * Scales the given quantity by the given factor.
     * @param q original quantity
     * @param factor number to multiply to the original quantity
     * @return the original quantity multiplied by factor
     */
    public static Quantity scale(Quantity q, float factor){
        /**
         * scales by multiplying.
         */
        return new Quantity(q.amount * factor, q.unit);
    }

    /**
     * Adds the amount of the same unit.
     * @param amount amount to add
     */
    public void add(float amount){
        /**
         * adds amount of same unit.
         */
        this.amount += amount;
    }

    private float amount;
    private String unit;

    /**
     *
     * @param amount amount expressed in number
     * @param unit the unit of the quantity
     */
    public Quantity(float amount, String unit){
        this.amount = amount;
        this.unit = unit;
    }

    public float getAmount(){
        return this.amount;
    }

    public String getUnit(){
        return this.unit;
    }
}
