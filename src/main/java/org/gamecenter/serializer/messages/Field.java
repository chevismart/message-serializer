package org.gamecenter.serializer.messages;

/**
 * Created by Chevis on 14-7-16.
 */
public class Field<T> {

    private String name;
    private int length;
    private String type;
    private T value;
    private boolean mandatory;
    private boolean checkNull;

    public Field() {
    }

    public Field(Field<T> field) {
        this.name = field.getName();
        this.length = field.getLength();
        this.type = field.getType();
        this.mandatory = field.isMandatory();
        this.checkNull = field.isCheckNull();
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String
    toString() {
        return "Field{" +
                "name='" + name + '\'' +
                ", length=" + length +
                ", type='" + type + '\'' +
                ", value=" + value +
                ", mandatory=" + mandatory +
                ", checkNull=" + checkNull +
                '}';
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Field field = (Field) o;

        if (checkNull != field.checkNull) return false;
        if (length != field.length) return false;
        if (mandatory != field.mandatory) return false;
        if (name != null ? !name.equals(field.name) : field.name != null) return false;
        if (type != null ? !type.equals(field.type) : field.type != null) return false;
        if (value != null ? !value.equals(field.value) : field.value != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + length;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (value != null ? value.hashCode() : 0);
        result = 31 * result + (mandatory ? 1 : 0);
        result = 31 * result + (checkNull ? 1 : 0);
        return result;
    }

    public boolean isCheckNull() {

        return checkNull;
    }

    public void setCheckNull(boolean checkNull) {
        this.checkNull = checkNull;
    }

    public boolean isMandatory() {

        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public int getLength() {

        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
