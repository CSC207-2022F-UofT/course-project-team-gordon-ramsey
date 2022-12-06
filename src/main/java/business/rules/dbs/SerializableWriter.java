package business.rules.dbs;

public abstract class SerializableWriter<T> extends SerializableIOHandler<T>{
    public abstract void write(T obj);
}
