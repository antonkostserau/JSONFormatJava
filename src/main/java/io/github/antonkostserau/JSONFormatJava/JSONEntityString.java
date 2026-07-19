package io.github.antonkostserau.JSONFormatJava;

public class JSONEntityString extends JSONEntity {
    
    private String value;

    public JSONEntityString(String value) {
        super(JSONEntity.STRING);
        
        this.setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    
    @Override
    public String toString() {
        String escaped = this.value.replace("\\", "\\\\").replace("\"", "\\\"");
        return "\"" + escaped + "\"";
    }

}
