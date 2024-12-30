package org.vera.amazingFactions.dto;

public class ItemDTO {
    private int id;
    private String name;
    private String description;
    private String faction_id;

    public ItemDTO(int id, String name, String description, String faction_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.faction_id = faction_id;
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

    public String getFaction_id() {
        return faction_id;
    }

    public void setFaction_id(String faction_id) {
        this.faction_id = faction_id;
    }
}
