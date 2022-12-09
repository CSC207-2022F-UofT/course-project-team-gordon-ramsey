package entities;

import java.io.Serializable;

/**
 * Represents an ingredient.
 */
public class Ingredient implements Serializable{
    private String name;
    private String description;
    private Quantity amount;

    /**
     *
     * @param name name of the ingredient
     * @param description short description of the ingredient
     * @param amount amount of the ingredient
     */
    public Ingredient(String name, String description, Quantity amount){
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    /**
     *
     * @param name name of the ingredient
     * @param amount amount of the ingredient
     */
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

    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if(!(o instanceof Ingredient)) return false;
        return (this.name.equals(((Ingredient) o).getName()));
    }
}
