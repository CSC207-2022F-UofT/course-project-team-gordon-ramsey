package entities;

import java.io.Serializable;

/**
 * Represents an ingredient.
 */
public class Ingredient implements Serializable{
    private String name;
    private String description;
    private Quantity amount;

    public Ingredient(String name, String description, Quantity amount){
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    public Ingredient(String name, Quantity amount){
        this.name = name;
        this.amount = amount;
        this.description = amount.getAmount() + " " + amount.getUnit() + " " + name;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public Quantity getQuantity(){
        return this.amount;
    }

    public void setQuantity(Quantity amount){
        this.amount = amount;
    }
}