package business.rules.dbs;

public class RecipeDB extends DB{
    public boolean validateSearchTerm(String searchTerm){
        //if searchTerm is a keyword in the recipe database, return true
        return true;
        //else return false
    }
}