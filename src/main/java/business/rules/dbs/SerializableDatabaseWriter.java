package business.rules.dbs;

public abstract class SerializableDatabaseWriter<T> extends SerializableIOHandler<T>{
    public abstract void write(T obj);
}
