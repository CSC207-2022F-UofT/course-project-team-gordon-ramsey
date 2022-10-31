package main.java.entities;

import java.io.Serializable;

/**
 * Represents a user profile.
 */
public class User implements Serializable{
    private String fullname;
    private String name;
    private GroceryList glist;
    private Journal journal;
}