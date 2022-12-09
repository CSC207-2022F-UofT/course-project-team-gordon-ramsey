import business.rules.Presenter;
import business.rules.dbs.SerializableIOHandler.DATABASE_TYPE;
import business.rules.dps.RecipeDataPacket;
import business.rules.dps.UserDataPacket;
import external.interfaces.CLI;
import external.interfaces.LocalReader;
import external.interfaces.LocalWriter;
import external.interfaces.NetReader;

public class run {
    public static void main(String[] args){
        LocalReader<RecipeDataPacket> rdb_r = new LocalReader<RecipeDataPacket>(DATABASE_TYPE.RECIPE_DATABASE);
        LocalWriter<RecipeDataPacket> rdb_w = new LocalWriter<RecipeDataPacket>(DATABASE_TYPE.RECIPE_DATABASE);
        LocalReader<UserDataPacket> udb_r = new LocalReader<UserDataPacket>(DATABASE_TYPE.USER_DATABASE);
        LocalWriter<UserDataPacket> udb_w = new LocalWriter<UserDataPacket>(DATABASE_TYPE.USER_DATABASE);
        NetReader api = new NetReader(null);
        CLI cli = new CLI(null);
        Presenter p = Presenter.buildPresenter(cli, udb_r, udb_w, rdb_r, rdb_w, api);
        p.start();
    }
}