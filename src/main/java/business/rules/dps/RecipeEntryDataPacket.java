package business.rules.dps;

import entities.RecipeEntry;

public class RecipeEntryDataPacket extends EntryDataPacket{
    public static RecipeEntry parse(RecipeEntryDataPacket edp){
        return new RecipeEntry(edp.time, RecipeDataPacket.parse(edp.recipe_dp));
    }

    public RecipeDataPacket recipe_dp;

    public RecipeEntryDataPacket(RecipeEntry re){
        super(re);
        this.recipe_dp = new RecipeDataPacket(re.getRecipe());
    }
}