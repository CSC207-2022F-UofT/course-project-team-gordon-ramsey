package entities;

import java.io.Serializable;

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

    public boolean validatePassword(String password) {
        return (this.password.equals(password));
    }
}