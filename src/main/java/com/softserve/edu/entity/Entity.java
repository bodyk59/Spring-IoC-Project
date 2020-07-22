package com.softserve.edu.entity;

public class Entity {
    private int id; // must be unique
    private String name;

    public Entity(String name) {
        ++this.id;
        this.name = name;
    }

    public int getId() {
        return id;
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
