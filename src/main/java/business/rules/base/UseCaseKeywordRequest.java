package business.rules.base;

import business.rules.dbs.DB;

/**
 * Manages request to search recipe using keyword
 */
public class UseCaseKeywordRequest extends UseCaseRequest{
    public String str;
    public boolean verbose;
    public DB db;

    /**
     *
     * @param stage useCase request stage
     * @param db database
     * @param verbose boolean representing whether the keyword used the appropriate number of words
     * @param str keyword from user
     */
    public UseCaseKeywordRequest(String str, DB db, boolean verbose, int stage){
        super(stage);
        this.str = str;
        this.db = db;
    }
}