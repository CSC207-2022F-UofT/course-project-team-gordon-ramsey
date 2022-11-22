package entities;

import java.io.Serializable;

/**
 * represents a quantity with a unit.
 */
public class Quantity implements Serializable{

    public static Quantity scale(Quantity q, float factor){
        /**
         * scales by multiplying.
         */
        return new Quantity(q.amount * factor, q.unit);
    }

    public void add(float amount){
        /**
         * adds amount of same unit.
         */
        this.amount += amount;
    }

    private float amount;
    private String unit;

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