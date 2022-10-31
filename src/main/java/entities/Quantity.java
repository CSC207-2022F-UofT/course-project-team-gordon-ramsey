package main.java.entities;

import java.io.Serializable;

/**
 * represents a quantity with a kitchen unit.
 */
public class Quantity implements Serializable{
    enum UNIT{
        GRAMS,
        OZ,
        CUPS,
        ML
    }

    private static final String grams = "GRAMS", oz = "OZ", cups = "CUPS", ml = "ML";
    /**
     *       GRAMS     OZ      CUPS     ML
     * GRAMS   1      28.2    1/200     0
     *  OZ   1/28.2     1    28.5/247  28.5
     * CUPS   200   247/28.5    1      247
     *  ML     0     1/28.5    1/247    1
     */
    private static final float[][] unit_map = {{1, 28.2, 1.0/200, 0},
                                               {1/28.2, 1, 28.5/247, 28.5},
                                               {200, 247/28.5, 1, 247},
                                               {0, 1/28.5, 1.0/247, 1}};

    public static int getInt(UNIT unit){
        switch(unit){
            case ML: return 3;
            case CUPS: return 2;
            case OZ: return 1;
            case GRAMS: ;
            default: return 0;
        }
    }

    public static UNIT getUnit(String unit){
        unit = unit.toUpperCase();   // assuming no descriptors like 'kilo'
        if(unit.indexOf(ml) >= 0){
            return UNIT.ML;
        }
        if(unit.indexOf(cups) >= 0){
            return UNIT.CUPS;
        }
        if(unit.indexOf(oz) >= 0){
            return UNIT.OZ;
        }
        return UNIT.GRAMS;
    }

    public static Quantity toUnit(Quantity q, UNIT unit){
        return new Quantity(q.amount * Quantity.unit_map[Quantity.getInt(q.unit)][Quantity.getInt(unit)], unit);
    }

    private float amount;
    private UNIT unit;

    public Quantity(int amount, UNIT unit){
        this.amount = amount;
        this.unit = unit;
    }

    public float getAmount(){
        return this.amount;
    }

    public UNIT getUnit(){
        return this.unit;
    }
}