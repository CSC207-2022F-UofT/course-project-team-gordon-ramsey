package entities;

import java.io.Serializable;

/**
 * Represents an instruction by pointing to source link.
 */
public class Instruction implements Serializable{
    private String source_link;

    /**
     *
     * @param source_link the link to the page with instructions
     */
    public Instruction(String source_link){
        this.source_link = source_link;
    }

    public String getInstruction(){
        return this.source_link;
    }
}
