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

    public static Map<String, User> UserDb = new HashMap<String, User>();

    public static void readDatabase() {
        try {
            FileInputStream fis = new FileInputStream(new File("D:\\GR_UserDB.txt"));
            ObjectInputStream ois = new ObjectInputStream(fis);
            User user = (User) ois.readObject();
            while (user != null) {
                UserDb.put(user.getName(), user);
                user = (User) ois.readObject();
            }
            fis.close();
            ois.close();
        }  catch(Exception ex){
            ex.printStackTrace();
        }
        }

    public static void updateDatabase() {
        try {
            FileOutputStream fos = new FileOutputStream(new File("D:\\GR_UserDB.txt"));
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (User user: UserDB.UserDb.values()) {
                oos.writeObject(user);
            }
            fos.close();
            oos.close();
        }  catch(Exception ex){
            ex.printStackTrace();
        }

    }


    public static void createFile() {
        try{
            File file = new File("c:\\GR_UserDB.txt");
            if (file.createNewFile()){ //return true
                System.out.println("New file is created!!");
            }else{ //return false
                System.out.println("GR_UserDB.txt already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public boolean addUser(User user) {
        if (UserDb.containsKey(user.getName())) {
            return false;
        } else {
            UserDb.put(user.getName(), user);
            return true;
        }
    }

    public boolean validateUser(String username) {
        return UserDb.containsKey(username);
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

