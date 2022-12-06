package business.rules.dbs;

public abstract class SerializableReader<T> extends SerializableIOHandler<T>{
    public abstract T[] read();
}
