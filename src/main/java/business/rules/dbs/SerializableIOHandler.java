package business.rules.dbs;

public abstract class SerializableIOHandler<T>{
    private static final String HOME = System.getProperty("user.home");
    private static final String OS = System.getProperty("os.name");
    private static final String RECIPE_FILE = "recipedb.obj";
    private static final String[] RECIPE_DIR = {"RecipeSelector"};

    public String getRecipeHome(){
        String result = HOME;
        char delim = '/';
        if(OS.indexOf("Windows") > 0)delim = '\\';
        for(int i = 0; i < RECIPE_DIR.length; i++){
            result += delim + RECIPE_DIR[i];
        }
        result += delim + RECIPE_FILE;
        return result;
    }

    public abstract void close();
}
