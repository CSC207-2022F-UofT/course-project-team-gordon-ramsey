package temp;

import java.io.Serializable;

/**
 * represents a quantity with a kitchen unit.
 */
class Quantity_ implements Serializable{
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
    private static final float[][] unit_map = {{1, 28.2f, 1.0f/200, 0},
                                               {1/28.2f, 1, 28.5f/247, 28.5f},
                                               {200, 247/28.5f, 1, 247},
                                               {0, 1/28.5f, 1.0f/247, 1}};

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
        if(unit.contains(ml)){
            return UNIT.ML;
        }
        if(unit.contains(cups)){
            return UNIT.CUPS;
        }
        if(unit.contains(oz)){
            return UNIT.OZ;
        }
        return UNIT.GRAMS;
    }

    public static Quantity_ toUnit(Quantity_ q, UNIT unit){
        return new Quantity_(q.amount * Quantity_.unit_map[Quantity_.getInt(q.unit)][Quantity_.getInt(unit)], unit);
    }

    public static Quantity_ add(Quantity_ q1, Quantity_ q2){
        /**
         * converts second quantity to that of first and returns sum of amount.
         */
        return new Quantity_(q1.amount + Quantity_.toUnit(q2, q1.unit).amount, q1.unit);
    }

    private float amount;
    private UNIT unit;

    public Quantity_(float amount, UNIT unit){
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