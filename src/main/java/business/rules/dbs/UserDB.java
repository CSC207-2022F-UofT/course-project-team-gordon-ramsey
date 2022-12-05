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

    Map<String, User> UserDb = new HashMap<String, User>();

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



    public boolean addUser(User user) {
        if (UserDb.containsKey(user.getName())) {
            return false;
        } else {
            UserDb.put(user.getName(), user);
            return true;
        }
    }

    public boolean validatePassword(String username, String password) {
        User temp = UserDb.get(username);
        return temp.validatePassword(password);
    }



    public boolean removeUser(User user) {
        if (UserDb.containsKey(user.getName())) {
            UserDb.remove(user.getName());
            return true;
        } else {
            return false;
        }
    }

   public User getUser(String username){
        return UserDb.get(username);
   }

}

