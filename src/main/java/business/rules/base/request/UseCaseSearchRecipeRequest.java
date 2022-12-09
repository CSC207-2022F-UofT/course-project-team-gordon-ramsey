package business.rules.base.request;

import business.rules.dbs.RecipeDB;

/**
 * Manages request to search recipe using keyword
 */
public class UseCaseSearchRecipeRequest extends UseCaseRequest{
    public String str;
    public boolean verbose;
    public RecipeDB rdb;

    /**
     * @param stage usecase request stage
     * @param rdb recipe database to search with.
     * @param verbose boolean representing whether the to show status updtaes are not.
     * @param str keyword from user
     */
    public UseCaseSearchRecipeRequest(String str, RecipeDB rdb, boolean verbose, int stage){
        super(stage);
        this.str = str;
        this.rdb = rdb;
        this.verbose = verbose;
    }
}