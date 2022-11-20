package business.rules.dbs;

import entities.User;

public class UserDB extends DB {

    User[] users = null;

    public boolean addUser(User user){
        //if successful
        return true;
    }

    public boolean validateUser(String username){
        //if username in DB return true
        return true;
        //else return false
    }

    public boolean validatePassword(String username, String password){
        //if password correct, return true
        return true;
        //else return false
    }
}
