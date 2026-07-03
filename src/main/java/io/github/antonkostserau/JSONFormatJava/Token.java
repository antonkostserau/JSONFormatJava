package io.github.antonkostserau.JSONFormatJava;

public class Token {

    public static final short ARRAY_ENABLE = 0b00000001;
    public static final short ARRAY_DISABLE = 0b00000010;

    public static final short OBJECT_ENABLE = 0b00000011;
    public static final short OBJECT_DISABLE = 0b00000100;

    public static final short COLON = 0b00000101;
    public static final short COMMA = 0b00000110;

    public static final short BOOLEAN = 0b00000111;
    public static final short NULL = 0b00001000;
    public static final short NUMBER = 0b00001001;
    public static final short STRING = 0b00001010;

    public static final short END = 0b00000000;

    private final short key;
    
    private final int begin;
    private final int end;
    
    public Token(short key) {
        this.key = key;
        
        this.begin = -1;
        this.end = -1;
    }
    
    public Token(short key, int begin, int end) {
        this.key = key;
        
        this.begin = begin;
        this.end = end;
    }

    public short getKey() {
        return key;
    }

    public int getBegin() {
        return begin;
    }

    public int getEnd() {
        return end;
    }

}
