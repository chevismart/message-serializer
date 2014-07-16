package org.gamecenter.serializer.messages;

/**
 * Created by Chevis on 14-7-16.
 */
public class Field<T> {

    private int length;

    private T value;

    private boolean isMandatory;

    private boolean isCheckNull;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        if (isCheckNull != field.isCheckNull) return false;
        if (isMandatory != field.isMandatory) return false;
        if (length != field.length) return false;
        if (value != null ? !value.equals(field.value) : field.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = length;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (isMandatory ? 1 : 0);
        result = 31 * result + (isCheckNull ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Field{" +
                "length=" + length +
                ", value=" + value +
                ", isMandatory=" + isMandatory +
                ", isCheckNull=" + isCheckNull +
                '}';
    }

    public void setMandatory(boolean isMandatory) {
        this.isMandatory = isMandatory;
    }

    public void setCheckNull(boolean checkNull) {
        this.isCheckNull = checkNull;
    }

    public boolean isMandatory() {
        return isMandatory;

    }

    public boolean isCheckNull() {
        return isCheckNull;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public int getLength() {

        return length;
    }

    public T getValue() {
        return value;
    }
}
