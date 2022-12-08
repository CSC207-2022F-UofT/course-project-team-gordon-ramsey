package business.rules.dps;

import java.io.Serializable;

import entities.Quantity;

public class QuantityDataPacket implements Serializable{
    public static Quantity parse(QuantityDataPacket qdp){
        return new Quantity(qdp.amount, qdp.unit);
    }

    public float amount;
    public String unit;

    public QuantityDataPacket(Quantity q){
        this.amount = q.getAmount();
        this.unit = q.getUnit();
    }
}