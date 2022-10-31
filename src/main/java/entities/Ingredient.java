package main.java.entities;

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
        this.description = "";
        this.amount = amount;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public Quantity getQuantity(){
        return this.quantity;
    }

    public void setQuantity(Quantity quantity){
        this.quantity = quantity;
    }
}