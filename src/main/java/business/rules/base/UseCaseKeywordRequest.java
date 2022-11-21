package business.rules.base;

import business.rules.dbs.DB;

public class UseCaseKeywordRequest extends UseCaseRequest{
    public String str;
    public DB db;

    public UseCaseKeywordRequest(String str, DB db, int stage){
        super(stage);
        this.str = str;
        this.db = db;
    }
}