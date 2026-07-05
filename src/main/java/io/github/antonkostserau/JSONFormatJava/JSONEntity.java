package io.github.antonkostserau.JSONFormatJava;

public abstract class JSONEntity {
    
    public static final short ARRAY = 0b00000001;
    public static final short OBJECT = 0b00000010;
    public static final short BOOLEAN = 0b00000011;
    public static final short NULL = 0b00000100;
    public static final short NUMBER = 0b00000101;
    public static final short STRING = 0b00000110;
    
    private final short type;
    
    public JSONEntity(short type) {
        this.type = type;
    }
    
//    public abstract JSONEntity getByIndex(int index);
//    public abstract void setByIndex(int index, JSONEntity entity);
//    
//    public abstract void add(JSONEntity entity);
//    public abstract void addByIndex(int index, JSONEntity entity);
//    
//    public abstract JSONEntity removeByIndex(int index);
//    
    
    public boolean isArray() {
        return this.type == ARRAY;
    }
    
    public boolean isObject() {
        return this.type == OBJECT;
    }
    
    public boolean isBoolean() {
        return this.type == BOOLEAN;
    }
    
    public boolean isNULL() {
        return this.type == NULL;
    }
    
    public boolean isNumber() {
        return this.type == NUMBER;
    }
    
    public boolean isString() {
        return this.type == STRING;
    }
    
    public JSONEntityArray asArray() {
        if (isArray())
            return (JSONEntityArray) this;
        
        throw new RuntimeException();
    }
    
    public JSONEntityObject asObject() {
        if (isObject())
            return (JSONEntityObject) this;
        
        throw new RuntimeException();
    }
    
    public JSONEntityBoolean asBoolean() {
        if (isBoolean())
            return (JSONEntityBoolean) this;
        
        throw new RuntimeException();
    }
    
    public JSONEntityNULL asNULL() {
        if (isNULL())
            return (JSONEntityNULL) this;
        
        throw new RuntimeException();
    }
    
    public JSONEntityNumber asNumber() {
        if (isNumber())
            return (JSONEntityNumber) this;
        
        throw new RuntimeException();
    }
    
    public JSONEntityString asString() {
        if (isString())
            return (JSONEntityString) this;
        
        throw new RuntimeException();
    }
    
}
