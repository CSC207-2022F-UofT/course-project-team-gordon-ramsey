package business.rules.dps;

import java.io.Serializable;

import entities.Ingredient;

public class IngredientDataPacket implements Serializable{
    public static Ingredient parse(IngredientDataPacket idp){
        return new Ingredient(idp.name, idp.description, QuantityDataPacket.parse(idp.quantity_dp));
    }

    public QuantityDataPacket quantity_dp;
    public String name;
    public String description;

    public IngredientDataPacket(Ingredient i){
        this.name = i.getName();
        this.description = i.getDescription();
        this.quantity_dp = new QuantityDataPacket(i.getQuantity());
    }
}