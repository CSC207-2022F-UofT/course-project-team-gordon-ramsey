package external.interfaces;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import business.rules.dbs.RecipeDataPacket;
import business.rules.dbs.SerializableDatabaseWriter;

public class LocalWriter<T> extends SerializableDatabaseWriter<T>{
    private ObjectOutputStream oos;
    private String write_path;

    public LocalWriter(){
        
    }

    public void init(){
        this.oos = new ObjectOutputStream(new FileOutputStream(""));
    }

    public void close(){

    }

    public boolean write(T dp){
        return false;
    }
}
