package business.rules.dps;

import java.io.Serializable;
import java.time.Instant;

import entities.Entry;

public abstract class EntryDataPacket implements Serializable{
    public static Entry parse(EntryDataPacket edp){
        if(edp instanceof SearchEntryDataPacket) return SearchEntryDataPacket.parse((SearchEntryDataPacket)edp);
        else if(edp instanceof RecipeEntryDataPacket) return RecipeEntryDataPacket.parse((RecipeEntryDataPacket)edp);
        return null;
    }

    public Instant time;

    public EntryDataPacket(Entry e){
        this.time = e.getTime();
    }
}