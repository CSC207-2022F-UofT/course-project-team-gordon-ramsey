package business.rules.base;

import business.rules.dbs.DB;

public class UseCaseKeywordRequest extends UseCaseRequest{
    public String str;
    public boolean verbose;
    public DB db;

    public UseCaseKeywordRequest(String str, DB db, boolean verbose, int stage){
        super(stage);
        this.str = str;
        this.db = db;
    }
}