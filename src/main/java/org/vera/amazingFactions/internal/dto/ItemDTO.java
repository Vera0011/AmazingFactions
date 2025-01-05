package org.vera.amazingFactions.internal.dto;

public class ItemDTO {
    private int id;
    private String name;
    private String description;
    private String factionId;

    public ItemDTO(int id, String name, String description, String factionId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.factionId = factionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFactionId() {
        return factionId;
    }

    public void setFactionId(String factionId) {
        this.factionId = factionId;
    }
}
