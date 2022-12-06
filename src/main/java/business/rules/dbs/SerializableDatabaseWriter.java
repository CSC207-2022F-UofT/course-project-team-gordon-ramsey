package business.rules.dbs;

public abstract class SerializableDatabaseWriter<T> extends SerializableIOHandler<T>{
    public abstract boolean write(T obj);
}
