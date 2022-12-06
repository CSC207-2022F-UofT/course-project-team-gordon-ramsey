package business.rules.dbs;

import java.io.File;

public abstract class SerializableIOHandler<T>{
    private static final String HOME = System.getProperty("user.home");
    private static final String OS = System.getProperty("os.name");
    private static final String RECIPE_FILE = "recipedb.obj", USER_FILE = "userdb.obj";
    private static final String[] RECIPE_DIR = {"RecipeSelector", "dbs"}, USER_DIR = {"RecipeSelector", "dbs"};

    public String getRecipeDatabaseHome(){
        return this.buildHome(RECIPE_DIR, RECIPE_FILE);
    }

    public String getUserDatabaseHome(){
        return this.buildHome(USER_DIR, USER_FILE);
    }

    public boolean localRecipeDatabaseExists(){
        return new File(this.getRecipeDatabaseHome()).exists();
    }

    public boolean localUserDatabaseExists(){
        return new File(this.getUserDatabaseHome()).exists();

    }

    private String buildHome(String[] dir, String file){
        String result = HOME;
        char delim = '/';
        if(OS.indexOf("Windows") > 0)delim = '\\';
        for(int i = 0; i < dir.length; i++){
            result += delim + dir[i];
        }
        result += delim + file;
        return result;
    }

    public abstract void close();
    public abstract void init();
}
