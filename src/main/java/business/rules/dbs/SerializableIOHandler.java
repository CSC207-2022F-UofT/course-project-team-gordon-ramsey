package business.rules.dbs;

import java.io.File;
import java.io.IOException;

public abstract class SerializableIOHandler<T>{
    private static final String HOME = System.getProperty("user.home");
    private static final String OS = System.getProperty("os.name");
    private static final String RECIPE_FILE = "recipedb.obj", USER_FILE = "userdb.obj";
    private static final String[] RECIPE_DIR = {"RecipeSelector", "dbs"}, USER_DIR = {"RecipeSelector", "dbs"};
    public static enum DATABASE_TYPE{
        RECIPE_DATABASE,
        USER_DATABASE
    }

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

    public boolean createRecipeDatabaseHome(){
        if(this.localRecipeDatabaseExists()) return false;
        try{
            new File(this.buildHomeDirectory(RECIPE_DIR, this.getPathDelim())).mkdirs();
            new File(this.getRecipeDatabaseHome()).createNewFile();
            return true;
        } catch(IOException e){}
        return false;
    }

    public boolean createUserDatabaseHome(){
        if(this.localUserDatabaseExists()) return false;
        try{
            new File(this.buildHomeDirectory(USER_DIR, this.getPathDelim())).mkdirs();
            new File(this.getUserDatabaseHome()).createNewFile();
            return true;
        } catch(IOException e){}
        return false;
    }

    private char getPathDelim(){
        if(OS.indexOf("Windows") >= 0)return '\\';
        return '/';
    }

    private String buildHome(String[] dir, String file){
        char delim = this.getPathDelim();
        return this.buildHomeDirectory(dir, delim) + delim + file;
    }

    private String buildHomeDirectory(String[] dir, char delim){
        String result = HOME;
        for(int i = 0; i < dir.length; i++){
            result += delim + dir[i];
        }
        return result;
    }

    public abstract boolean close();
    public abstract boolean init();
}
