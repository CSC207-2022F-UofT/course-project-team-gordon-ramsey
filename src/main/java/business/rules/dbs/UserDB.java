package business.rules.dbs;
import entities.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import business.rules.Presenter;
import business.rules.dps.UserDataPacket;

public class UserDB implements DB{
    public static UserDB getLocalInstance(SerializableDatabaseReader<UserDataPacket> sr,
                                          SerializableDatabaseWriter<UserDataPacket> sw, Presenter presenter){
        /*
         * PRECONDITION: sr, sw, presenter not null.
         */
        if(!sr.localUserDatabaseExists()){
            if(!sr.createUserDatabaseHome()){
                presenter.showUser("Failed to create new local user database.");
                return null;
            }
            presenter.showUser("Created new local user database.");
            return new UserDB(sw, presenter);
        }
        sr.init();
        List<UserDataPacket> udps = sr.read();
        sr.close();
        if(udps != null){
            presenter.showUser("Read " + udps.size() + " users from local user database.");
            return new UserDB(sw, presenter, udps);
        }
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

    /**
     * Adds a new User object to the database.
     * @param user new User to be added
     * @return true if username is not already in the database and addition is successful; false if username already exists and addition fails
     */
    public boolean addUser(User user){
        if(this.hasUser(user.getUsername())){
            this.presenter.showUser("Username already exists.");
            return false;
        }
        udb.put(user.getUsername(), user);
        return true;
    }

    /**
     * Checks if a User with certain username already exists in the database.
     * @param username the username string to be searched from the database
     * @return true if a User with given username exists, false if no such User exists
     */
    public boolean hasUser(String username){
        return udb.containsKey(username);
    }

    /**
     * Validates if the given password string matches the password of the User specified by the given username.
     * @param username username of the User to be validated
     * @param password password to validate
     * @return true if a User with the given username exists and its password matches the given password; false if the user doesn't exist or the given password doesn't match the user's password
     */
    public boolean validateCredentials(String username, String password){
        if(this.hasUser(username)){
            if(udb.get(username).matchPassword(password)) return true;
            this.presenter.showUser("Password for username did not match.");
            return false;
        }
        this.presenter.showUser("Username not found.");
        return false;
    }

    /**
     * Removes the given User from the database.
     * @param user the User object to be removed from the database
     * @return true if the specified user exists and removal is successful; false if the user doesn't exist
     */
    public boolean removeUser(User user){
        return this.removeUser(user.getUsername());
    }

    /**
     * Removes the User with the given username from the database.
     * @param username the username of the User to be removed
     * @return true if the username exists and removal is successful; false if such username doesn't exist
     */
    public boolean removeUser(String username){
        if(this.hasUser(username)){
            udb.remove(username);
            return true;
        }
        return false;
    }

    /**
     * Returns a user with the specified username.
     * @param username the username of the User to search for
     * @return the User with the given username if exists; null if no such User exists
     */
   public User getUser(String username){
        if(this.hasUser(username)) return udb.get(username);
        return null;
    }

    /**
     * Writes the changes into a local file.
     */
    public void close(){
        this.presenter.showUser("Closing local user database.");
        if(!sw.init()){
            this.presenter.showUser("Failed to initialize writing user database.");
            return;
        }
        int count = 0;
        for(User user : udb.values()){
            if(!sw.write(new UserDataPacket(user))){
                this.presenter.showUser("Failed to write one of the local users, continuing..");
            }
            else count++;
        }
        this.presenter.showUser("Wrote " + count + " / " + udb.size() + " users to the local system.");
        if(!sw.close()){
            this.presenter.showUser("Failed to finish writing user database.");
            return;
        }
    }
}
