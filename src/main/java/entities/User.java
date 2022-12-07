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

    public boolean matchPassword(String password){
        return (this.password.equals(password));
    }

    public void addToGroceryList(Recipe r){
        this.glist.addIngredients(r.getIngredients());
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }
}
