package main.java.entities;

import java.io.Serializable;

/**
 * Represents an instruction.
 */
public class Instruction implements Serializable{
    private String instruction;
    private Ingredient[] ingredients;

    public Instruction(String instruction, Ingredient[] ingredients){
        this.instruction = instruction;
        this.ingredients = ingredients;
    }

    public String getInstruction(){
        return this.instruction;
    }

    public Ingredient[] getIngredients(){
        return this.ingredients;
    }
}