package org.gamecenter.serializer.messages;

import java.util.List;

/**
 * Created by Chevis on 14-7-16.
 */
public class Message<T> {

    private String name;
    private int id;
    private String direction;
    private String description;
    private List<Field> fields;

    public String getDirection() {

        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        if (id != message.id) return false;
        if (description != null ? !description.equals(message.description) : message.description != null) return false;
        if (direction != null ? !direction.equals(message.direction) : message.direction != null) return false;
        if (fields != null ? !fields.equals(message.fields) : message.fields != null) return false;
        if (name != null ? !name.equals(message.name) : message.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + (direction != null ? direction.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (fields != null ? fields.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", direction='" + direction + '\'' +
                ", description='" + description + '\'' +
                ", fields=" + fields +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }
}
