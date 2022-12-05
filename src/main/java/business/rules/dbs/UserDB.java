package business.rules.dbs;
import entities.User;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

// Need one static function to load database from the local system (from computer objectreader/object writer)
// needs to be serializable
// reading from a file and typecast to the userDB type
// functionality : add user, remove user, modify user, update name, update password
// see if the user has a file already, if not, make a new one, if they do then you get the location and use as input stream
// thread/ asynch /every time

public class UserDB implements Serializable {

    Map<String, String> UserDb = new HashMap<String, String>();

//    public static UserDB loadDatabase() {
//        try{
//            FileInputStream fis = new FileInputStream(new File("D:\\javaObjects.txt")); // system user home here,
//            ObjectInputStream ois = new ObjectInputStream(fis);
//            UserDB db = (UserDB) ois.readObject();
//            fis.close();
//            ois.close();
//            return db;
//        }catch(Exception ex){
//            ex.printStackTrace();
//        }
//        return
//    }



//    public void addUser(User user) {
//        UserDb.put(user.getName(), user.getPassword());
//    }

    public void removeUser(User user) {
        UserDb.remove(user.getName());
    }

   public String getUser(User user){
        return user.getFullName();
   }

}

