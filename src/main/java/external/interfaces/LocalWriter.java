package external.interfaces;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import business.rules.dbs.SerializableDatabaseWriter;

/*
 * Generic Type only RecipeDataPacket OR UserDataPacket
 */
public class LocalWriter<T> extends SerializableDatabaseWriter<T>{
    private ObjectOutputStream oos;
    private String write_path;

    public LocalWriter(DATABASE_TYPE type){
        if(type == DATABASE_TYPE.RECIPE_DATABASE) this.write_path = this.getRecipeDatabaseHome();
        else if(type == DATABASE_TYPE.USER_DATABASE) this.write_path = this.getUserDatabaseHome();
        else this.write_path = null;
    }

    public boolean init(){
        if(this.write_path == null) return false;
        try{
            this.oos = new ObjectOutputStream(new FileOutputStream(this.write_path));
            return true;
        }
        catch(IOException e){}
        return false;
    }

    public boolean close(){
        try{
            this.oos.close();
            return true;
        }
        catch(IOException e){}
        return false;
    }

    public boolean write(T dp){
        try{
            this.oos.writeObject(dp);
            return true;
        }
        catch(IOException e){}
        return false;
    }
}