package business.rules.dbs;
import entities.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.rules.Presenter;

public class UserDB implements DB{
    public static UserDB getLocalInstance(SerializableDatabaseReader<UserDataPacket> sr,
                                          SerializableDatabaseWriter<UserDataPacket> sw, Presenter presenter){
        /*
         * PRECONDITION: sr, sw, presenter not null.
         */
        if(!sr.localUserDatabaseExists()) return new UserDB(sw, presenter);
        sr.init();
        List<UserDataPacket> udps = sr.read();
        sr.close();
        if(udps != null) return new UserDB(sw, presenter, udps);
        return null;
    }

    private SerializableDatabaseWriter<UserDataPacket> sw;
    private Map<String, User> udb;
    private Presenter presenter;

    private UserDB(){
        this.sw = null;
        this.udb = null;
        this.presenter = null;
    }

    private UserDB(SerializableDatabaseWriter<UserDataPacket> sw, Presenter presenter){
        this.sw = sw;
        this.presenter = presenter;
        this.udb = new HashMap<String, User>();
    }

    private UserDB(SerializableDatabaseWriter<UserDataPacket> sw, Presenter presenter, List<UserDataPacket> udps){
        this.sw = sw;
        this.presenter = presenter;
        this.udb = new HashMap<String, User>();
        User tmp;
        for(UserDataPacket udp : udps){
            if(udp == null) continue;
            tmp = UserDataPacket.parse(udp);
            udb.put(tmp.getUsername(), tmp);
        }
    }

    public boolean addUser(User user){
        if(this.hasUser(user.getUsername())){
            this.presenter.showUser("Username already exists.");
            return false;
        }
        udb.put(user.getUsername(), user);
        return true;
    }

    public boolean hasUser(String username){
        return udb.containsKey(username);
    }

    public boolean validateCredentials(String username, String password){
        if(this.hasUser(username)){
            if(udb.get(username).matchPassword(password)) return true;
            this.presenter.showUser("Password for username did not match.");
            return false;
        }
        this.presenter.showUser("Username not found.");
        return false;
    }

    public boolean removeUser(User user){
        return this.removeUser(user.getUsername());
    }

    public boolean removeUser(String username){
        if(this.hasUser(username)){
            udb.remove(username);
            return true;
        }
        return false;
    }

   public User getUser(String username){
        if(this.hasUser(username)) return udb.get(username);
        return null;
    }

    public void close(){
        this.presenter.showUser("Closing local user database.");
        sw.init();
        int count = 0;
        for(User user : udb.values()){
            if(!sw.write(new UserDataPacket(user))){
                this.presenter.showUser("Failed to write one of the local users, continuing..");
            }
            else count++;
        }
        this.presenter.showUser("Wrote " + count + " / " + udb.size() + " users to the local system.");
        sw.close();
    }
}