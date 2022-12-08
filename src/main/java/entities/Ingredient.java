package entities;

import java.io.Serializable;

/**
 * Represents an ingredient.
 */
public class Ingredient implements Serializable{
    public static Ingredient parse(String ingredient){
        /*
         * PRECONDITION: format : <float amount> <unit> <description>
         */
        int start = 0;
        while(start < ingredient.length() && !Character.isDigit(ingredient.charAt(start))) start++;
        if(start == ingredient.length()) return new Ingredient("", ingredient, new Quantity(0, ""));
        int end = start + 1, dots = 0;
        while(end < ingredient.length()){
            if(ingredient.charAt(end) == '.'){
                if(dots >= 1) break;
                dots++;
                end++;
            }
            else if(Character.isDigit(ingredient.charAt(end))){
                end++;
            }
            else break;
        }
        float amount = Float.parseFloat(ingredient.substring(start, end));
        if(end == ingredient.length()) return new Ingredient("", ingredient, new Quantity(amount, ""));
        String left_part = ingredient.substring(0, start).trim();
        String right_part = ingredient.substring(end).trim();
        int space_ind = right_part.indexOf(" "), comma_ind = right_part.indexOf(","), right_start = -1;
        if(comma_ind == -1) right_start = space_ind;
        else if(space_ind == -1) right_start = space_ind;
        else right_start = Math.min(space_ind, comma_ind);
        if(right_start == -1)return new Ingredient("", ingredient, new Quantity(amount, ""));
        if(left_part.length() > 0){
            return new Ingredient(left_part, ingredient, new Quantity(amount, right_part.substring(0, right_start).trim()));
        }
        else{
            return new Ingredient(right_part.substring(right_start + 1), new Quantity(amount, right_part.substring(0, right_start).trim()));
        }
    }
 
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

    @Override
    public boolean equals(Object o){
        if (o == null) return false;
        if(!(o instanceof Ingredient)) return false;
        return (this.name.equals(((Ingredient) o).getName()));
    }
}
