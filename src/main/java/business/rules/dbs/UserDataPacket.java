package business.rules.dbs;

import entities.*;

public class UserDataPacket {
    public static User parse(UserDataPacket udp){
        return new User(udp.fullname, udp.username, udp.password, udp.glist, udp.journal);
    }

    public String fullname;
    public GroceryList glist;
    public Journal journal;
    public String username;
    public String password;

    public UserDataPacket(User user){
        this.fullname = user.getFullName();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.glist = user.getGroceryList();
        this.journal = user.getJournal();
    }
}
