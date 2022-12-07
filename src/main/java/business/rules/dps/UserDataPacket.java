package business.rules.dps;

import java.io.Serializable;

import entities.*;

public class UserDataPacket implements Serializable{
    public static User parse(UserDataPacket udp){
        return new User(udp.fullname, udp.username, udp.password, GroceryListDataPacket.parse(udp.glist_dp), JournalDataPacket.parse(udp.journal_dp));
    }

    public String fullname;
    public GroceryListDataPacket glist_dp;
    public JournalDataPacket journal_dp;
    public String username;
    public String password;

    public UserDataPacket(User user){
        this.fullname = user.getFullName();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.glist_dp = new GroceryListDataPacket(user.getGroceryList());
        this.journal_dp = new JournalDataPacket(user.getJournal());
    }
}