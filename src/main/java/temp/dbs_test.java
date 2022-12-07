package temp;

import java.time.Duration;
import java.time.Instant;

import business.rules.Presenter;
import business.rules.dbs.UserDB;
import business.rules.dbs.SerializableIOHandler.DATABASE_TYPE;
import business.rules.dps.RecipeDataPacket;
import business.rules.dps.UserDataPacket;
import entities.GroceryList;
import entities.Ingredient;
import entities.Instruction;
import entities.Journal;
import entities.Quantity;
import entities.Recipe;
import entities.SearchEntry;
import entities.User;
import external.interfaces.CLI;
import external.interfaces.LocalReader;
import external.interfaces.LocalWriter;
import external.interfaces.NetReader;

public class dbs_test {
    public static void main(String[] args) {
        LocalReader<RecipeDataPacket> rdb_r = new LocalReader<RecipeDataPacket>(DATABASE_TYPE.RECIPE_DATABASE);
        LocalWriter<RecipeDataPacket> rdb_w = new LocalWriter<RecipeDataPacket>(DATABASE_TYPE.RECIPE_DATABASE);
        LocalReader<UserDataPacket> udb_r = new LocalReader<UserDataPacket>(DATABASE_TYPE.USER_DATABASE);
        LocalWriter<UserDataPacket> udb_w = new LocalWriter<UserDataPacket>(DATABASE_TYPE.USER_DATABASE);
        NetReader api = new NetReader(null);
        CLI cli = new CLI(null);
        Presenter p = Presenter.buildPresenter(cli, udb_r, udb_w, rdb_r, rdb_w, api);
        UserDB udb = p.getUserDB();
        /*User usr = new User("parth singh", "panther251", "1234");
        GroceryList glist = usr.getGroceryList();
        Journal journal = usr.getJournal();
        glist.addIngredient(new Ingredient("salt", new Quantity(100, "mg")));
        glist.addIngredient(new Ingredient("pepper", new Quantity(30, "mg")));
        glist.addIngredient(new Ingredient("sugar", new Quantity(200, "g")));
        journal.addEntry(new SearchEntry(Instant.now(), "chicken masala"));
        Ingredient[] ingredients = {new Ingredient("water", new Quantity(500, "ml")), new Ingredient("flour", new Quantity(100, "mg"))};
        Recipe r = new Recipe("pasta", "nice italian pasta dinner", ingredients, new Instruction("www.google.com"), Duration.ofMinutes(120), 2.0f);
        journal.addFavourite(r);
        udb.addUser(usr);*/
        //udb.removeUser("panther251");
        cli.showMessage(udb.getUser("panther251").getFullName());
        cli.showMessage(udb.getUser("panther251").getName());
        cli.showMessage(udb.getUser("panther251").getPassword());
        cli.showMessage(udb.getUser("panther251").getUsername());
        cli.showCollection(udb.getUser("panther251").getGroceryList().getCollection());
        cli.showCollection(udb.getUser("panther251").getJournal().getCollection());
        p.close();
    }
}
