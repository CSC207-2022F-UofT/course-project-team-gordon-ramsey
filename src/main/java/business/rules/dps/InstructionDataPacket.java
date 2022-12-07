package business.rules.dps;

import java.io.Serializable;

import entities.Instruction;

public class InstructionDataPacket implements Serializable {
    public static Instruction parse(InstructionDataPacket idp){
        return new Instruction(idp.source_link);
    }

    public String source_link;

    public InstructionDataPacket(Instruction i){
        this.source_link = i.getInstruction();
    }
}