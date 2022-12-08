package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a user profile.
 */
public class User implements Serializable{
    private String fullname;
    private String name;
    private GroceryList glist;
    private Journal journal;
    private String username;
    private String password;
    private Recipe[] planner;

    public User(String fullname, String username, String password){
        this.fullname = fullname;
        this.name = fullname.split(" ")[0];
        this.username = username;
        this.password = password;
        this.glist = null;
        this.journal = null;
    }

    public Journal getJournal(){
        return this.journal;
    }

    public GroceryList getGroceryList(){
        return this.glist;
    }

    public String getName(){
        return this.name;
    }

    public String getFullName(){
        return this.fullname;
    }

    /**
     * Compares a given string to the user's password.
     * @param password A string to be compared to the user's password
     * @return true if the given string matches the user's password, false if they don't match
     */
    public boolean validatePassword(String password) {
        return (this.password.equals(password));
    }

    /**
     * Adds an ingredient to the user's grocery list.
     * @param newItem an Ingredient object to be added to the user's grocery list
     */
    public void addToGroceryList(Ingredient newItem) {
        this.glist.addIngredient(newItem);
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public void saveChanges(){
        // if required.
    }
}
