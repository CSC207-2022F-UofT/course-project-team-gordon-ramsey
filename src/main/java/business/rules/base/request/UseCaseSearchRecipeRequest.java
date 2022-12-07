package business.rules.base.request;

import business.rules.dbs.RecipeDB;

public class UseCaseSearchRecipeRequest extends UseCaseRequest{
    public String str;
    public boolean verbose;
    public RecipeDB rdb;

    public UseCaseSearchRecipeRequest(String str, RecipeDB rdb, boolean verbose, int stage){
        super(stage);
        this.str = str;
        this.rdb = rdb;
    }
}