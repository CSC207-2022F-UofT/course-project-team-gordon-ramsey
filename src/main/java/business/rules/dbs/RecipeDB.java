package business.rules.dbs;

import entities.*;
import business.rules.Presenter;
import business.rules.api.*;
import business.rules.dps.RecipeDataPacket;

import java.time.Duration;
import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class RecipeDB implements DB{

    /**
     * Generates a RecipeDB object from serialized data
     * @param sr SerializableDatabaseReader that reads data packets from the serialized local file
     * @param sw SerializableDatabaseWriter assigned to the RecipeDB object that serializes data packets to save in local file
     * @param api APIReader assigned to the RecipeDB that reads recipe data from external API
     * @param presenter Presenter to be associated to the RecipeDB
     * @return a RecipeDB containing Recipe objects read from local file (if exists)
     */
    public static RecipeDB getLocalInstance(SerializableDatabaseReader<RecipeDataPacket> sr,
                                            SerializableDatabaseWriter<RecipeDataPacket> sw,
                                            APIReader api, Presenter presenter){
        /*
         * PRECONDITION: sr, sw, api, presenter not null.
         */
        if(!sr.localRecipeDatabaseExists()){
            if(!sr.createRecipeDatabaseHome()){
                presenter.showUser("Failed to create new local recipe database.");
                return null;
            }
            presenter.showUser("Created new local recipe database.");
            return new RecipeDB(sw, api, presenter);
        }

        sr.init();
        List<RecipeDataPacket> rdps = sr.read();
        sr.close();
        if(rdps != null){
            presenter.showUser("Read " + rdps.size() + " recipes from local recipe database.");
            return new RecipeDB(sw, api, presenter, rdps);
        }
        return null;
    }

    private APIReader api;
    private SerializableDatabaseWriter<RecipeDataPacket> sw;
    private Map<String, Recipe> lrdb;
    private Presenter presenter;

    private RecipeDB(){
        this.api = null;
        this.sw = null;
        this.lrdb = null;
        this.presenter = null;
    }

    private RecipeDB(SerializableDatabaseWriter<RecipeDataPacket> sw, APIReader api, Presenter presenter){
        this.sw = sw;
        this.api = api;
        this.presenter = presenter;
        this.lrdb = new HashMap<String, Recipe>();
    }

    private RecipeDB(SerializableDatabaseWriter<RecipeDataPacket> sw, APIReader api,
                     Presenter presenter, List<RecipeDataPacket> rdps){
        this.sw = sw;
        this.api = api;
        this.presenter = presenter;
        this.lrdb = new HashMap<String, Recipe>();
        Recipe tmp;
        for(RecipeDataPacket rdp : rdps){
            if(rdp == null) continue;
            tmp = RecipeDataPacket.parse(rdp);
            lrdb.put(tmp.getName(), tmp);
        }
    }

    /**
     * Searches for recipes
     * @param keyword A keyword string to search for
     * @param skip_atleast
     * @param size_atleast
     * @param verbose
     * @return
     */
    public Recipe[] getRecipes(String keyword, int skip_atleast, int size_atleast, boolean verbose){
        /**
         * PRECONDITION: skip <= min(size_atleast, RECIPIES_PER_MINUTE_CAP)
         */
        String[][] info = this.api.request(new APIRequest(keyword, skip_atleast, Math.min(size_atleast, APIReader.RECIPES_PER_MINUTE_CAP)), verbose).data;
        if(info == null) return new Recipe[0];
        Recipe[] recipes = new Recipe[info.length];
        for(int i = 0; i < info.length; i++){
            if(info[i] ==  null) break;
            Ingredient[] ingredients = new Ingredient[(info[i].length - 5) / 4];
            for(int j = 5, k = 0; j < info[i].length; j += 4, k += 1){
                ingredients[k] = new Ingredient(info[i][j + 3], info[i][j], new Quantity(Float.parseFloat(info[i][j + 1]), info[i][j + 2]));
            }
            recipes[i] = new Recipe(info[i][0], info[i][1], ingredients, new Instruction(info[i][2]), Duration.ofMinutes((long)Float.parseFloat(info[i][3])), Float.parseFloat(info[i][4]));
        }
        return recipes;
    }

    public boolean addLocalRecipe(Recipe recipe){
        if(this.hasLocalRecipe(recipe.getName())){
            this.presenter.showUser("Recipe already exists.");
            return false;
        }
        lrdb.put(recipe.getName(), recipe);
        return true;
    }

    public boolean hasLocalRecipe(String name){
        return lrdb.containsKey(name);
    }

    public boolean removeLocalRecipe(Recipe recipe){
        return this.removeLocalRecipe(recipe.getName());
    }

    public boolean removeLocalRecipe(String name){
        if(this.hasLocalRecipe(name)){
            lrdb.remove(name);
            return true;
        }
        return false;
    }

    public Recipe getLocalRecipe(String name){
        if(this.hasLocalRecipe(name)) return lrdb.get(name);
        return null;
    }

    public String[][][] getCollection(){
        String[][][] recipes = new String[this.lrdb.size()][][];
        int i = 0;
        for(Recipe r : this.lrdb.values()){
            recipes[i++] = r.getCollection();
        }
        return recipes;
    }

    public void close(){
        this.api.stopClocks();
        this.presenter.showUser("Closing local recipe database.");
        if(!sw.init()){
            this.presenter.showUser("Failed to initialize writing recipe database.");
            return;
        }
        int count = 0;
        for(Recipe recipe : lrdb.values()){
            if(!sw.write(new RecipeDataPacket(recipe))){
                this.presenter.showUser("Failed to write one of the local recipes, continuing..");
            }
            else count++;
        }
        this.presenter.showUser("Wrote " + count + " / " + lrdb.size() + " recipes to the local system.");
        if(!sw.close()){
            this.presenter.showUser("Failed to finish writing recipe database.");
            return;
        }
    }
}
