package com.softserve.edu.entity;

import javax.persistence.Id;

public class Entity {
    @Id
    private int id;
    private String name;
    private static int counter;

    public Entity(String name) {
        this.id = ++counter;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity)) return false;

        Entity entity = (Entity) o;

        if (getId() != entity.getId()) return false;
        return getName() != null ? getName().equals(entity.getName()) : entity.getName() == null;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return String.format("Entity id: %d, name: %s", id, name);
    }
}
