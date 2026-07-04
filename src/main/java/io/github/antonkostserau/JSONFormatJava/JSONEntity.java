package io.github.antonkostserau.JSONFormatJava;

public abstract class JSONEntity {
    
    public abstract JSONEntity getByIndex(int index);
    public abstract void setByIndex(int index, JSONEntity entity);
    
    public abstract JSONEntity getByKey(String key);
    public abstract void setByKey(String key, JSONEntity entity);
    
    public abstract void add(JSONEntity entity);
    public abstract void addByIndex(int index, JSONEntity entity);
    
    public abstract JSONEntity removeByIndex(int index);
    public abstract JSONEntity removeByKey(String key);
    
    public abstract void clear();
    
    public abstract int getCount();
    
    public abstract boolean isEntityArray();
    public abstract boolean isEntityObject();
    public abstract boolean isEntityBoolean();
    public abstract boolean isEntityNULL();
    public abstract boolean isEntityNumber();
    public abstract boolean isEntityString();
    
}
