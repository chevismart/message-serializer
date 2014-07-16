package org.gamecenter.serializer.messages;

import java.util.List;

/**
 * Created by Chevis on 14-7-16.
 */
public class Message<T> {

    private String name;
    private byte id;
    private String description;
    private List<Field> fields;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != message.id) return false;
        if (description != null ? !description.equals(message.description) : message.description != null) return false;
        if (fields != null ? !fields.equals(message.fields) : message.fields != null) return false;
        if (name != null ? !name.equals(message.name) : message.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (int) id;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (fields != null ? fields.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", description='" + description + '\'' +
                ", fields=" + fields +
                '}';
    }

    public String getName() {
        return name;
    }

    public byte getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setId(byte id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
