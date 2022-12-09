package entities;

/**
 * Represents a user profile.
 */
public class User{
    private String fullname;
    private String name;
    private GroceryList glist;
    private Journal journal;
    private String username;
    private String password;

    /**
     *
     * @param fullname Full name of the new user
     * @param username Username of the user
     * @param password password of the user
     * @param glist grocery list of the user
     * @param journal journal of the user
     */
    public User(String fullname, String username, String password, GroceryList glist, Journal journal){
        this.fullname = fullname;
        this.name = fullname.split(" ")[0];
        this.username = username;
        this.password = password;
        this.glist = glist;
        this.journal = journal;
    }

    public User(String fullname, String username, String password){
        this.fullname = fullname;
        this.name = fullname.split(" ")[0];
        this.username = username;
        this.password = password;
        this.glist = new GroceryList();
        this.journal = new Journal();
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
    public boolean matchPassword(String password) {

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
