package io.github.antonkostserau.JSONFormatJava;

import java.math.BigDecimal;
import java.math.BigInteger;

public class JSONEntityNumber extends JSONEntity {

    private String value;

    public JSONEntityNumber(String value) {
        super(JSONEntity.NUMBER);

        this.setValue(value);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getInt() {
        return Integer.parseInt(this.value);
    }

    public long getLong() {
        return Long.parseLong(this.value);
    }

    public float getFloat() {
        return Float.parseFloat(this.value);
    }

    public double getDouble() {
        return Double.parseDouble(this.value);
    }

    public BigInteger getBigInteger() {
        return new BigInteger(this.value);
    }

    public BigDecimal getBigDecimal() {
        return new BigDecimal(this.value);
    }

    public void setInt(int value) {
        this.value = Integer.toString(value);
    }

    public void setLong(long value) {
        this.value = Long.toString(value);
    }

    public void setFloat(float value) {
        this.value = Float.toString(value);
    }

    public void setDouble(double value) {
        this.value = Double.toString(value);
    }

    public void setBigInteger(BigInteger value) {
        this.value = value.toString();
    }

    public void setBigDecimal(BigDecimal value) {
        this.value = value.toString();
    }

    @Override
    public String toString() {
        return this.value;
    }
    
    public static JSONEntityNumber fromInt(int value) {
        return new JSONEntityNumber(Integer.toString(value));
    }

    public static JSONEntityNumber fromLong(long value) {
        return new JSONEntityNumber(Long.toString(value));
    }

    public static JSONEntityNumber fromFloat(float value) {
        return new JSONEntityNumber(Float.toString(value));
    }

    public static JSONEntityNumber fromDouble(double value) {
        return new JSONEntityNumber(Double.toString(value));
    }

    public static JSONEntityNumber fromBigInteger(BigInteger value) {
        return new JSONEntityNumber(value.toString());
    }

    public static JSONEntityNumber fromBigDecimal(BigDecimal value) {
        return new JSONEntityNumber(value.toString());
    }

}
