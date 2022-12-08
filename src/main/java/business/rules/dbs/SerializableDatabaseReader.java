package business.rules.dbs;

import java.util.List;

public abstract class SerializableDatabaseReader<T> extends SerializableIOHandler<T>{
    public abstract List<T> read();
}
