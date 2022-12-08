package business.rules.base.response;

import business.rules.ui.UI.FIELD_TYPE;
import business.rules.ui.UI.MODIFICATION_TYPE;

public class UseCaseFieldQueryResponse extends UseCaseResponse{
    public String[] field;
    public MODIFICATION_TYPE mtype;
    public FIELD_TYPE ftype;

    public UseCaseFieldQueryResponse(RETURN_CODE rCode, ACTION_CODE aCode, String[] field, MODIFICATION_TYPE mtype, FIELD_TYPE ftype){
        super(rCode, aCode);
        this.field = field;
        this.mtype = mtype;
        this.ftype = ftype;
    }    
}