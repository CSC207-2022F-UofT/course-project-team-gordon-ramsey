package external.interfaces;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;
import java.util.ArrayList;

import business.rules.dbs.SerializableDatabaseReader;

/*
 * Generic Type only RecipeDataPacket OR UserDataPacket
 */
public class LocalReader<T> extends SerializableDatabaseReader<T>{
    private ObjectInputStream ois;
    private String read_path;

    public LocalReader(DATABASE_TYPE type){
        if(type == DATABASE_TYPE.RECIPE_DATABASE) this.read_path = this.getRecipeDatabaseHome();
        else if(type == DATABASE_TYPE.USER_DATABASE) this.read_path = this.getUserDatabaseHome();
        else this.read_path = null;
    }

    public boolean init(){
        if(this.read_path == null) return false;
        try{
            this.ois = new ObjectInputStream(new FileInputStream(this.read_path));
            return true;
        }
        catch(IOException e){
            return false;
        }

    }

    public boolean close(){
        try{
            this.ois.close();
            return true;
        }
        catch(IOException | NullPointerException e){}
        return false;
    }

    public List<T> read(){
        ArrayList<T> list = new ArrayList<T>();
        try{
            while(true){
                // safe because preconditions of use.
                @SuppressWarnings("unchecked") T tmp = (T) this.ois.readObject();
                list.add(tmp);
            }
        }
        catch(EOFException | NullPointerException e){
            return list;
        }
        catch(IOException | ClassNotFoundException e){}
        return null;
    }
}
