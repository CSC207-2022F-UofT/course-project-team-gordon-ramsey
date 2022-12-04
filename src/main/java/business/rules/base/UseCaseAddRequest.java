package business.rules.base;

import business.rules.dbs.RecipeDB;

public class UseCaseAddRequest extends UseCaseRequest{

    private String Name;
    private String Description;
    private String[][] Ingredients;
    private String Instructions;
    private String new_cook_time;
    private String newYield;
    private RecipeDB rdb;

    public UseCaseAddRequest(int stage, String name, String description, String[][] Ingredients, String Instructions,
                             String new_cook_time, String newYield, RecipeDB rdb) {
        super(stage);
    }
}
