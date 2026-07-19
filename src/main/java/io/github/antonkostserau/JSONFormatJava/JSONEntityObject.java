package io.github.antonkostserau.JSONFormatJava;

import java.util.Map;
import java.util.HashMap;
import java.util.StringJoiner;

public class JSONEntityObject extends JSONEntity {

    private final Map<String, JSONEntity> data;

    public JSONEntityObject() {
        super(JSONEntity.OBJECT);

        this.data = new HashMap<String, JSONEntity>();
    }

    public JSONEntity get(String key) {
        return this.data.get(key);
    }

    public JSONEntity put(String key, JSONEntity entity) {
        return this.data.put(key, entity);
    }

    public JSONEntity remove(String key) {
        return this.data.remove(key);
    }

    public void clear() {
        this.data.clear();
    }

    public int getCount() {
        return this.data.size();
    }

    public Map<String, JSONEntity> toMap() {
        return new HashMap<String, JSONEntity>(this.data);
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(",", "{", "}");

        for (Map.Entry<String, JSONEntity> entry : this.data.entrySet())
            joiner.add("\"" + entry.getKey().replace("\\", "\\\\").replace("\"", "\\\"") + "\":" + entry.getValue());

        return joiner.toString();
    }

}
